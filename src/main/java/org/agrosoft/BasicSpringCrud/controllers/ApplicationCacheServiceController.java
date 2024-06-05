package org.agrosoft.BasicSpringCrud.controllers;

import java.util.Collection;

import org.agrosoft.BasicSpringCrud.cache.EscolaridadCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/cacheServices")
@RestController
@Slf4j
public class ApplicationCacheServiceController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private EscolaridadCacheManager applicationCacheManager;
	
	@GetMapping("/cacheNames")
	private ResponseEntity<Collection<String>> fetchCacheNames() {
		log.info("fetchCacheNames llamado");
		return ResponseEntity.ok(cacheManager.getCacheNames());
	}
	
	@GetMapping("/fetchCacheValues")
	private ResponseEntity<Cache> fetchCacheValues(@RequestParam String cacheName) {
		log.info("fetchCacheValues llamado");
		return ResponseEntity.ok(cacheManager.getCache(cacheName));
	}
	
	@GetMapping("/fetchEscolaridades")
	private ResponseEntity<?> fetchEscolaridades(@RequestParam int escolaridadId) {
		log.info("fetchEscolaridades llamado");
		try {
			return ResponseEntity.ok(applicationCacheManager.fetchEscolaridadById(escolaridadId));
		} catch (Exception e) {
			log.error("Excepción capturada en fetchEscolaridades: {}", e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/clearCache")
	private ResponseEntity<String> clearCache(@RequestParam String cacheName) {
		log.info("clearCache llamado");
		cacheManager.getCache(cacheName).clear();
		return ResponseEntity.ok(cacheName + " cache fue borrado");
	}
}
