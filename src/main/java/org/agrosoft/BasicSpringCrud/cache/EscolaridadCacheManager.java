package org.agrosoft.BasicSpringCrud.cache;

import java.util.Objects;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.dtos.Escolaridad;
import org.agrosoft.BasicSpringCrud.services.EscolaridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableCaching
@Slf4j
public class EscolaridadCacheManager {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private EscolaridadService escolaridadService;
	
	
	@Cacheable(value="escolaridadForId", key="#idEscolaridad")
	public Optional<Escolaridad> fetchEscolaridadById(int idEscolaridad) {
		log.info("Obteniendo Escolaridad con id: " + idEscolaridad);
		long startTime = System.currentTimeMillis();
		try {
			return escolaridadService.encontrarEscolaridad(idEscolaridad);
		} catch (Exception e) {
			log.error("Excepción inesperada al buscar Escolaridad: " + e.getMessage());
			return Optional.empty();
		} finally {
			log.info("Obtención de escolaridad terminada en {} ms", (System.currentTimeMillis()-startTime));
		}
	}
	
	@Scheduled(cron="${time.for.cache.clearing.cron}", zone = "America/Mexico_City")
	public void clearCache() {
		log.info("Borrando cache -- START");
		Cache cache = cacheManager.getCache("EscolaridadForId");
		if(Objects.nonNull(cache)) {
			cache.clear();
		}
		log.info("Borrando cache -- END");
	}
	
}
