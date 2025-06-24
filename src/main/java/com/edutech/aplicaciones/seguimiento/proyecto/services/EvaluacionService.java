package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Evaluacion;

public interface EvaluacionService {

    List<Evaluacion> findAll();

    Optional<Evaluacion> findById(Long id);

    Evaluacion save(Evaluacion evaluacion);

    Optional<Evaluacion> delete(Evaluacion evaluacion);
}
