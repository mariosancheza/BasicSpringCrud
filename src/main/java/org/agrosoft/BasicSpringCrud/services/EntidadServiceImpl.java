package org.agrosoft.BasicSpringCrud.services;

import java.util.List;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.repositories.EntidadDao;
import org.agrosoft.BasicSpringCrud.dtos.Entidad;
import org.agrosoft.BasicSpringCrud.exceptions.EntidadFederativaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EntidadServiceImpl implements EntidadService {
	
	@Autowired
	private EntidadDao entidadDao;

	@Override
	public List<Entidad> listarTodasLasEntidades() {
		return (List<Entidad>) entidadDao.findAll(Sort.by("nombreEntidad"));
	}

	@Override
	public Optional<Entidad> encontrarEntidad(int id) {
		return entidadDao.findById(id);
	}

	@Override
	public Entidad encontrarEntidadPorNombre(String nombre) throws EntidadFederativaNotFoundException {
		return entidadDao.findEntidadByNombre(nombre).orElseThrow(() -> new EntidadFederativaNotFoundException("No existe la entidad con nombre: " + nombre));
	}

	@Override
	public List<Entidad> encontrarEntidadPorNombreParcial(String nombre) {
		return entidadDao.findAllEntidadByNombreLike(nombre);
	}
}
