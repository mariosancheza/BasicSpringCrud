package org.agrosoft.BasicSpringCrud.controllers;

import java.util.List;
import java.util.Objects;

import org.agrosoft.BasicSpringCrud.dtos.Persona;
import org.agrosoft.BasicSpringCrud.exceptions.PersonaNotFoundException;
import org.agrosoft.BasicSpringCrud.exceptions.PersonaValidationException;
import org.agrosoft.BasicSpringCrud.services.EntidadService;
import org.agrosoft.BasicSpringCrud.services.EscolaridadService;
import org.agrosoft.BasicSpringCrud.services.PersonaService;
import org.agrosoft.BasicSpringCrud.utils.ErrorUtils;
import org.agrosoft.BasicSpringCrud.dtos.Escolaridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private EntidadService entidadService;
	
	@Autowired
	private EscolaridadService escolaridadService;
	
	@GetMapping("/todas")
	private List<Persona> getAllPersonas() {
		log.info("Obteniendo todas las personas desde la BD");
		return personaService.listarTodasLasPersonas();
	}
	
	@GetMapping("/porId/{id}")
	private Persona getPersonaById(@PathVariable("id") int idBuscado) {
		Persona aBuscar = Persona.builder().idPersona(idBuscado).build();
		Persona rersult = null;
		try {
			rersult = personaService.encontrarPersona(aBuscar);
		} catch (PersonaNotFoundException e) {
			log.error("No se encontró Persona con Id: {}. Devolviendo un valor null", idBuscado);
		}
		return rersult;
	}
	
	@GetMapping("/porRangoDeEdad")
	private List<Persona> getPersonasByAgeRange(
			@RequestParam(value="edadMin", required=false, defaultValue = "0") Integer min,
			@RequestParam(value="edadMax", required=false, defaultValue = "100") Integer max
			) {
		return personaService.listarPersonasPorRangoDeEdad(min, max);
	}
	
	@GetMapping("/porNombreParcial")
	private List<Persona> getPersonaByPartialName(@RequestParam("nombreParcial") String partialName) {
		return personaService.listaPersonasPorNombreParcial(partialName);
	}
	
	@GetMapping("/porPrimerApellido")
	private List<Persona> getPersonaByPrimerApellido(@RequestParam("primerApellido") String primerApellido) {
		return personaService.listaPersonasPorPrimerApellido(primerApellido);
	}
	
	@GetMapping("/porNombre")
	private Persona getPersonaByName(@RequestParam("nombre") String name) {
		Persona rersult = null;
		try {
			rersult = personaService.encuentraPersonaPorNombre(name);
		} catch (PersonaNotFoundException e) {
			log.error("No se encontró Persona con nombre: {}. Devolviendo un valor null", name);
		}
		return rersult;
	}
	
	@DeleteMapping("/borrarPersona/{id}")
	private ResponseEntity<?> deletePersonaById(@PathVariable("id") int idParaBorrar) {
		ResponseEntity<?> response;
		Persona aBorrar = Persona.builder().idPersona(idParaBorrar).build();
		try {
			aBorrar = personaService.encontrarPersona(aBorrar);
			personaService.eliminaPersona(aBorrar);
			response = ResponseEntity.ok("La persona con id: " + idParaBorrar + " fue borrada exitosamente");
		} catch (PersonaNotFoundException e) {
			log.info("La persona con Id: {} ya no existía en la base de datos. No se realiza ningún cambio", idParaBorrar);
			response = ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("La persona con id: " + idParaBorrar + " ya no existe en la base de datos");
		}
		return response;
	}
	
	@PostMapping("/agregarPersona")
	private ResponseEntity<?> addPersona(@Valid @RequestBody Persona newPersona, Errors errores) {
		ResponseEntity<?> response;
		log.info("Entrando a agregar persona");
		try {
			if(errores.hasErrors()) {
				throw new PersonaValidationException("Detalle: " + ErrorUtils.errorsToStringSet(errores));
			}
			newPersona.setEntidadNacimiento(entidadService.encontrarEntidadPorNombre(newPersona.getEntidadNacimiento().getNombreEntidad()));
			newPersona.setNivelEscolaridad(escolaridadService.encontrarEscolaridadPorNombre(newPersona.getNivelEscolaridad().getEscolaridad()));
			personaService.guardaPersona(newPersona);
			log.info("{} se ha guardado correctamente en la base de datos", newPersona.getNombrePersona());
			response = ResponseEntity.ok(newPersona.getNombrePersona() + " se ha guardado correctamente en la base de datos");
		} catch(Exception e) {
			String detalle;
			HttpStatus retStatus;
			if(e instanceof DataIntegrityViolationException) {
				detalle = "Error al insertar el registro en la BD. " + e.getMessage();
				retStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			} else if(e instanceof PersonaValidationException) {
				detalle = "Error al validar persona. " + e.getMessage();
				retStatus = HttpStatus.BAD_REQUEST;
			} else {
				detalle = "Ocurrió un error inesperado. " + e.getMessage();
				retStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			response = ResponseEntity
					.status(retStatus)
					.body(detalle);
			log.error(detalle);
		}
		return response;
	}
	

	
	@PutMapping("/actualizarPersonaUsandoPut/{id}")
	private ResponseEntity<?> actualizaPersonaConPut(@PathVariable int id, @Valid @RequestBody Persona persona, Errors errores) {
		log.info("Entrando a actualizar persona con id: {}", id);
		ResponseEntity<?> response;
		try {
			if(errores.hasErrors()) {
				throw new Exception(ErrorUtils.errorsToStringSet(errores).toString());
			}
			Persona actual = Persona.builder().idPersona(id).build();
			log.debug("Persona encontrada: {}", actual);
			if(Objects.nonNull(actual)){
				actual.setNombrePersona( persona.getNombrePersona() );
				actual.setPrimerApellido( persona.getPrimerApellido() );
				actual.setSegundoApellido( persona.getSegundoApellido() );
				actual.setFechaNacimiento( persona.getFechaNacimiento() );
				actual.setEntidadNacimiento( entidadService.encontrarEntidadPorNombre(persona.getEntidadNacimiento().getNombreEntidad()) );
				actual.setNivelEscolaridad( escolaridadService.encontrarEscolaridadPorNombre(persona.getNivelEscolaridad().getEscolaridad()) );
				personaService.guardaPersona(actual);
				response = ResponseEntity.ok("La persona con id " + id + " se ha actualizado correctamente en la base de datos");
			} else {
				throw new Exception("La persona no existe en la base de datos");
			}
		} catch (Exception e) {
			response = ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
			log.error("Ocurrió un error al intentar actualizar a la persona con id: {}. Detalle: {}", id, e.getMessage());
		}
		return response;
	}
	
	@PatchMapping("/actualizarPersonaUsandoPut/{id}")
	private ResponseEntity<?> actualizaPersonaConPatch(@PathVariable int id, @RequestParam String nuevoNivelEscolaridad) {
		ResponseEntity<?> response;
		Escolaridad nuevaEscolaridad;
		Persona persona;
		try {
			nuevaEscolaridad = escolaridadService.encontrarEscolaridadPorNombre(nuevoNivelEscolaridad);
			persona = Persona.builder().idPersona(id).build();
			persona = personaService.encontrarPersona(persona);
			if(Objects.nonNull(nuevaEscolaridad) && Objects.nonNull(persona)) {
				persona.setNivelEscolaridad(nuevaEscolaridad);
				personaService.guardaPersona(persona);
				response = ResponseEntity.ok("La persona con id " + id + " se ha actualizado correctamente en la base de datos");
			} else {
				throw new PersonaNotFoundException("La persona o la escolaridad no existen en la base de datos");
			}
		} catch (Exception e) {
			response = ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
			log.error(e.getMessage());
		}
		return response;
	}
	
	
}
