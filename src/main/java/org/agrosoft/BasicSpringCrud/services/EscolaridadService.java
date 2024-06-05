package org.agrosoft.BasicSpringCrud.services;

import java.util.List;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.dtos.Escolaridad;
import org.agrosoft.BasicSpringCrud.exceptions.NivelEscolaridadNotFoundException;

public interface EscolaridadService {
	public List<Escolaridad> listarTodasLasEscolaridades();
	public Optional<Escolaridad> encontrarEscolaridad(int idEntidad);
	public Escolaridad encontrarEscolaridadPorNombre(String nombre) throws NivelEscolaridadNotFoundException;
	public List<Escolaridad> encontrarEscolaridadPorNombreParcial(String nombre);
}
