package org.agrosoft.BasicSpringCrud.controllers;

import java.util.List;

import org.agrosoft.BasicSpringCrud.dtos.Entidad;
import org.agrosoft.BasicSpringCrud.exceptions.EntidadFederativaNotFoundException;
import org.agrosoft.BasicSpringCrud.services.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/entidades")
public class EntidadController {
	
	@Autowired
	private EntidadService service;
	
	@GetMapping("/todas")
	private List<Entidad> getAllEntidades() {
		log.info("Devolviendo todas las entidades");
		return service.listarTodasLasEntidades();
	}
	
	@GetMapping("/porId/{id}")
	private Entidad getEntidad(@PathVariable("id") int id) {
		log.info("Devolviendo entidad con id: {}", id);
		Entidad result = null;
		try {
			result = service.encontrarEntidad(id).orElseThrow(() -> new EntidadFederativaNotFoundException("No existe entidad con id: " + id));
		} catch (EntidadFederativaNotFoundException e) {
			log.error("No se encontró Entidad Federativa con id: {}. Devolviendo un valor null", id);
		}
		return result;
	}
	
	@GetMapping("/porNombre")
	private Entidad getEntidadPorNombre(@RequestParam("nombreEntidad") String nombreEntidad) {
		log.info("Obteniendo entidad cuyo nombre sea: {}", nombreEntidad);
		Entidad result = null;
		try {
			result = service.encontrarEntidadPorNombre(nombreEntidad);
		} catch (EntidadFederativaNotFoundException e) {
			log.error("No se encontró Entidad Federativa con nombre: {}. Devolviendo un valor null", nombreEntidad);
		}
		return result;
	}
	
	@GetMapping("/porNombreParcial")
	private List<Entidad> getEntidadPorNombreParcial(@RequestParam("nombreEntidad") String nombreEntidad) {
		log.info("Obteniendo entidad cuyo nombre contenga: {}", nombreEntidad);
		return service.encontrarEntidadPorNombreParcial(nombreEntidad);
	}
	
}
