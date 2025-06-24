package com.edutech.aplicaciones.seguimiento.proyecto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Pregunta;

import java.util.List;

public interface PreguntaRepository extends CrudRepository<Pregunta, Long> {
    List<Pregunta> findByEvaluacionId(Long evaluacionId);

}