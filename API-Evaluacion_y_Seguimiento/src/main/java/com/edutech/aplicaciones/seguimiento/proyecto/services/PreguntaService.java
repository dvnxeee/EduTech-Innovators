package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Pregunta;

public interface PreguntaService {

    List<Pregunta> findAll();

    Optional<Pregunta> findById(Long id);

    Pregunta save(Pregunta pregunta);

    Optional<Pregunta> delete(Pregunta pregunta);

    List<Pregunta> findByEvaluacionId(Long evaluacionId);

}