package org.agrosoft.BasicSpringCrud.services;

import java.util.List;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.repositories.EscolaridadDao;
import org.agrosoft.BasicSpringCrud.dtos.Escolaridad;
import org.agrosoft.BasicSpringCrud.exceptions.NivelEscolaridadNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EscolaridadServiceImpl implements EscolaridadService {
	
	@Autowired
	private EscolaridadDao escolaridadDao;

	@Override
	public List<Escolaridad> listarTodasLasEscolaridades() {
		return (List<Escolaridad>) escolaridadDao.findAll();
	}

	@Override
	public Optional<Escolaridad> encontrarEscolaridad(int id) {
		log.info("Buscando escolaridad desde base de datos, con id: {}", id);
		return escolaridadDao.findById(id);
	}

	@Override
	public Escolaridad encontrarEscolaridadPorNombre(String nombre) throws NivelEscolaridadNotFoundException {
		return escolaridadDao.findEscolaridadByNombre(nombre).orElseThrow(() -> new NivelEscolaridadNotFoundException("No existe el nivel de escolaridad con nombre: " + nombre));
	}

	@Override
	public List<Escolaridad> encontrarEscolaridadPorNombreParcial(String nombre) {
		return escolaridadDao.findAllEscolaridadesByNombreLike(nombre);
	}

}
