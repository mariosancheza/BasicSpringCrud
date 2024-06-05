package org.agrosoft.BasicSpringCrud.repositories;

import java.util.List;
import java.util.Optional;

import org.agrosoft.BasicSpringCrud.dtos.Entidad;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EntidadDao extends CrudRepository<Entidad, Integer> {
	@Query(value="SELECT e.* FROM entidad e WHERE e.nombre_entidad = :nombre", nativeQuery=true)
	Optional<Entidad> findEntidadByNombre(@Param("nombre") String nombreDeEntidad);
	
	@Query(value="SELECT e.* FROM entidad e WHERE e.nombre_entidad LIKE %:nombre%", nativeQuery=true)
	List<Entidad> findAllEntidadByNombreLike(@Param("nombre") String nombreDeEntidad);
	
	Optional<Entidad> findById(int id);

	List<Entidad> findAll(Sort sortBy);
}
