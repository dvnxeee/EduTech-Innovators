package com.edutech.contenido.repository;

import com.edutech.contenido.entities.Contenido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepository extends CrudRepository<Contenido, Long> {

}
