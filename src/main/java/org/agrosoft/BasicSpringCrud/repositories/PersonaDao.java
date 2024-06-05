package org.agrosoft.BasicSpringCrud.repositories;

import java.util.List;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.dtos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonaDao extends JpaRepository<Persona,Integer> {
	@Query(value="SELECT p.* FROM persona p WHERE TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) BETWEEN :min AND :max", nativeQuery=true)
	List<Persona> findByAgeRange(@Param("min") int edadMin, @Param("max") int edadMax);
	
	@Query(value="SELECT p.* FROM persona p WHERE nombre LIKE %:nombreParcial%", nativeQuery=true)
	List<Persona> findByPartialName(@Param("nombreParcial") String nombre);
	
	@Query(value="SELECT p.* FROM persona p WHERE nombre = :nombre", nativeQuery=true)
	Optional<Persona> findByName(@Param("nombre") String nombre);
	
	List<Persona> findByPrimerApellido(String primerApellido);
	
	List<Persona> findAllByOrderByIdPersonaDesc();
}
