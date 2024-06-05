package org.agrosoft.BasicSpringCrud.controllers;

import java.util.List;

import org.agrosoft.BasicSpringCrud.dtos.Escolaridad;
import org.agrosoft.BasicSpringCrud.exceptions.NivelEscolaridadNotFoundException;
import org.agrosoft.BasicSpringCrud.services.EscolaridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/escolaridades")
public class EscolaridadController {
	
	@Autowired
	private EscolaridadService service;
	
	@GetMapping("/todas")
	private List<Escolaridad> getAllEscolaridades() {
		log.info("Devolviendo todas las entidades");
		return service.listarTodasLasEscolaridades();
	}
	
	@GetMapping("/porId/{id}")
	private Escolaridad getEscolaridad(@PathVariable("id") int id) {
		log.info("Devolviendo entidad con id: {}", id);
		Escolaridad result = null;
		try {
			result = service.encontrarEscolaridad(id).orElseThrow(() -> new NivelEscolaridadNotFoundException("No existe Escolaridad con id: " + id));
		} catch (NivelEscolaridadNotFoundException e) {
			log.error("No se encontró Nivel de Escolaridad con id: {}. Devolviendo un valor null", id);
		}
		return result;
	}
	
	@GetMapping("/porNombre")
	private Escolaridad getEscolaridadPorNombre(@RequestParam("nombreEscolaridad") String nombreEscolaridad) {
		log.info("Obteniendo entidad cuyo nombre sea: {}", nombreEscolaridad);
		Escolaridad result = null;
		try {
			result = service.encontrarEscolaridadPorNombre(nombreEscolaridad);
		} catch (NivelEscolaridadNotFoundException e) {
			log.error("No se encontró Nivel de Escolaridad con nombre: {}. Devolviendo un valor null", nombreEscolaridad);
		}
		return result;
	}
	
	@GetMapping("/porNombreParcial")
	private List<Escolaridad> getEscolaridadPorNombreParcial(@RequestParam("nombreEscolaridad") String nombreEscolaridad) {
		log.info("Obteniendo entidad cuyo nombre contenga: {}", nombreEscolaridad);
		return service.encontrarEscolaridadPorNombreParcial(nombreEscolaridad);
	}

}
