package org.agrosoft.BasicSpringCrud.services;

import java.util.List;

import org.agrosoft.BasicSpringCrud.repositories.PersonaDao;
import org.agrosoft.BasicSpringCrud.dtos.Persona;
import org.agrosoft.BasicSpringCrud.exceptions.PersonaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	private PersonaDao repository;

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarTodasLasPersonas() {
		return repository.findAllByOrderByIdPersonaDesc();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encontrarPersona(Persona persona) throws PersonaNotFoundException {
		return repository.findById(persona.getIdPersona()).orElseThrow(() -> new PersonaNotFoundException("No existe persona con Id: " + persona.getIdPersona()));
	}

	@Override
	@Transactional
	public void guardaPersona(Persona persona) {
		repository.saveAndFlush(persona);
	}

	@Override
	@Transactional
	public void eliminaPersona(Persona persona) {
		repository.delete(persona);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarPersonasPorRangoDeEdad(int edadMin, int edadMax) {
		return repository.findByAgeRange(edadMin, edadMax);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listaPersonasPorNombreParcial(String nombreP) {
		return repository.findByPartialName(nombreP);
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encuentraPersonaPorNombre(String nombre) throws PersonaNotFoundException {
		return repository.findByName(nombre).orElseThrow(() -> new PersonaNotFoundException("No existe persona con nombre: " + nombre));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listaPersonasPorPrimerApellido(String pApellido) {
		return repository.findByPrimerApellido(pApellido);
	}
	
	
}
