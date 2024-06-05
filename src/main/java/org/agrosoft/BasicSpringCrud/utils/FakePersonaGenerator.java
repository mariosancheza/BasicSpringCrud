package org.agrosoft.BasicSpringCrud.utils;

import static org.agrosoft.BasicSpringCrud.constants.FakePersonaConstants.INCLUDE;
import static org.agrosoft.BasicSpringCrud.constants.FakePersonaConstants.NATIONALITY;
import static org.agrosoft.BasicSpringCrud.constants.FakePersonaConstants.NOINFO;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Random;

import org.agrosoft.BasicSpringCrud.cache.EscolaridadCacheManager;
import org.agrosoft.BasicSpringCrud.dtos.Entidad;
import org.agrosoft.BasicSpringCrud.dtos.Escolaridad;
import org.agrosoft.BasicSpringCrud.dtos.Persona;
import org.agrosoft.BasicSpringCrud.exceptions.NivelEscolaridadNotFoundException;
import org.agrosoft.BasicSpringCrud.services.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FakePersonaGenerator {
	
	@Autowired
	private AppConfigProvider configManager;
	
	@Autowired
	private EscolaridadCacheManager cacheManager;
	
	@Autowired
	private EntidadService entidadService;
	
	public Persona getFakePersona() {
		URL url;
		HttpURLConnection connection;
		InputStream responseStream;
		ObjectMapper mapper;
		JsonNode root;
		Persona response = null;
		try {
			url = UriComponentsBuilder
					.fromUriString(configManager.getBaseUrl())
					.queryParam(NATIONALITY, configManager.getNat())
					.queryParam(INCLUDE, configManager.getInc())
					.queryParam(NOINFO)
					.build()
					.toUri()
					.toURL();
			log.info("Llamando servicio: {}", url.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("accept", "application/json");
			responseStream = connection.getInputStream();
			mapper = new ObjectMapper();
			root = mapper.readTree(responseStream);
			
			String nombre = root.get("results").get(0).get("name").get("first").asText();
			String pApellido = root.get("results").get(0).get("name").get("last").asText();
			String sApellido = root.get("results").get(0).get("location").get("street").get("name").asText();
			sApellido = sApellido.substring(sApellido.lastIndexOf(" ")+1);
			LocalDate fechaNac = Instant.parse(root.get("results").get(0).get("dob").get("date").asText()).atZone(ZoneOffset.UTC).toLocalDate();
			Entidad estado = entidadService.encontrarEntidadPorNombre(root.get("results").get(0).get("location").get("state").asText());
			Escolaridad escolaridad = getRandomEscolaridad();
			response = Persona.builder()
					.nombrePersona(nombre)
					.primerApellido(pApellido)
					.segundoApellido(sApellido)
					.fechaNacimiento(fechaNac)
					.entidadNacimiento(estado)
					.nivelEscolaridad(escolaridad)
					.build();
			log.info("Persona obtenida: {}", response);
		} catch (Exception e) {
			log.error("Excepción al generar persona fake: {}", e.getMessage());
		}
		return response;
	}
	
	private Escolaridad getRandomEscolaridad() throws NivelEscolaridadNotFoundException {
		Random random = new Random();
		int id = random.nextInt(10)+1;
		return cacheManager.fetchEscolaridadById(id).orElseThrow(() -> new NivelEscolaridadNotFoundException("No se encontró escolaridad con id: " + id));
	}
}
