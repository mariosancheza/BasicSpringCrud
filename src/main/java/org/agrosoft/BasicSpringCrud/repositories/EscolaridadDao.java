package org.agrosoft.BasicSpringCrud.repositories;

import java.util.List;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.dtos.Escolaridad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EscolaridadDao extends CrudRepository<Escolaridad, Integer> {
	@Query(value="SELECT e.* FROM escolaridad e WHERE e.escolaridad = :nombre", nativeQuery=true)
	Optional<Escolaridad> findEscolaridadByNombre(@Param("nombre") String tipoEscolaridad);
	
	@Query(value="SELECT e.* FROM escolaridad e WHERE e.escolaridad LIKE %:nombre%", nativeQuery=true)
	List<Escolaridad> findAllEscolaridadesByNombreLike(@Param("nombre") String tipoEscolaridad);
	
	Optional<Escolaridad> findById(int id);
}
