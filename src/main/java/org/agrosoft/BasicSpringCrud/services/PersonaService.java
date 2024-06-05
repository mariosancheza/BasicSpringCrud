package org.agrosoft.BasicSpringCrud.services;

import java.util.List;

import org.agrosoft.BasicSpringCrud.dtos.Persona;
import org.agrosoft.BasicSpringCrud.exceptions.PersonaNotFoundException;

public interface PersonaService {
	public List<Persona> listarTodasLasPersonas();
	public Persona encontrarPersona(Persona persona) throws PersonaNotFoundException;
	public void guardaPersona(Persona persona);
	public void eliminaPersona(Persona persona);
	
	public List<Persona> listarPersonasPorRangoDeEdad(int edadMin, int edadMax);
	public List<Persona> listaPersonasPorNombreParcial(String nombreP);
	public Persona encuentraPersonaPorNombre(String nombre) throws PersonaNotFoundException;
	
	public List<Persona> listaPersonasPorPrimerApellido(String pApellido);
}
