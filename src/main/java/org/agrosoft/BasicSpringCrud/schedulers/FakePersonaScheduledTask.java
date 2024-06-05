package org.agrosoft.BasicSpringCrud.schedulers;

import org.agrosoft.BasicSpringCrud.dtos.Persona;
import org.agrosoft.BasicSpringCrud.services.PersonaService;
import org.agrosoft.BasicSpringCrud.utils.FakePersonaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FakePersonaScheduledTask {
	
	@Autowired
	private FakePersonaGenerator fakeGenerator;
	
	@Autowired
	private PersonaService personaService;
	
	@Scheduled(cron="${time.for.fakepersona.generation.cron}")
	private void getAndsaveFakePersona() {
		try {
			log.info("* * * * * * * * * * * * * Corriendo proceso automático de creación de personas * * * * * * * * * * *");
			long startTime = System.currentTimeMillis();
			Persona fake = fakeGenerator.getFakePersona(); 
			log.info("Persona fake generada en {} ms", (System.currentTimeMillis()-startTime));
			startTime = System.currentTimeMillis();
			personaService.guardaPersona(fake);
			log.info("Persona fake guardada en {} ms", (System.currentTimeMillis()-startTime));
		} catch(Exception e) {
			log.error("Error en tarea programada [Insertar fake persona]: {}", e.getMessage());
		}
	}
}
