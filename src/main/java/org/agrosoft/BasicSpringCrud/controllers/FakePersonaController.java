package org.agrosoft.BasicSpringCrud.controllers;


import org.agrosoft.BasicSpringCrud.dtos.Persona;
import org.agrosoft.BasicSpringCrud.services.PersonaService;
import org.agrosoft.BasicSpringCrud.utils.FakePersonaGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/personasFake")
public class FakePersonaController {
	
	@Autowired
	private FakePersonaGenerator fakeGenerator;
	
	@Autowired
	private PersonaService personaService;
	
	
	@GetMapping("/generaFakePersona")
	private Persona getFakePersona() {
		log.info("Generando fake persona");
		long startTime = System.currentTimeMillis();
		Persona fake = fakeGenerator.getFakePersona(); 
		log.info("Persona fake generada en {} ms", (System.currentTimeMillis()-startTime));
		return fake;
	}
	
	@GetMapping("/generaYGuardaFakePersona")
	private ResponseEntity<?> getAndsaveFakePersona() {
		ResponseEntity<?> response;
		try {
			long startTime = System.currentTimeMillis();
			Persona fake = fakeGenerator.getFakePersona(); 
			log.info("Persona fake generada en {} ms", (System.currentTimeMillis()-startTime));
			startTime = System.currentTimeMillis();
			personaService.guardaPersona(fake);
			log.info("Persona fake guardada en {} ms", (System.currentTimeMillis()-startTime));
			response = ResponseEntity.ok(fake.getNombrePersona() + " se ha guardado correctamente en la base de datos");
		} catch(Exception e) {
			String detalle;
			if(e instanceof DataIntegrityViolationException) {
				detalle = "Error al insertar el registro en la BD. " + e.getMessage();
			} else {
				detalle = "Ocurrió un error inesperado. " + e.getMessage();
			}
			response = ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(detalle);
			log.error(detalle);
		}
		return response;
		
	}
	
}
