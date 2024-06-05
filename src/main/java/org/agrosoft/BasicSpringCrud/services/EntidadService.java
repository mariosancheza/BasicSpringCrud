package org.agrosoft.BasicSpringCrud.services;

import java.util.List;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.dtos.Entidad;
import org.agrosoft.BasicSpringCrud.exceptions.EntidadFederativaNotFoundException;

public interface EntidadService {
	public List<Entidad> listarTodasLasEntidades();
	public Optional<Entidad> encontrarEntidad(int idEntidad);
	public Entidad encontrarEntidadPorNombre(String nombre) throws EntidadFederativaNotFoundException;
	public List<Entidad> encontrarEntidadPorNombreParcial(String nombre);
}
