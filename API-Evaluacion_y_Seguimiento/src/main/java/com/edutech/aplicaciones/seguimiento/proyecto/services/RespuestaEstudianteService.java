package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.RespuestaEstudiante;

public interface RespuestaEstudianteService {

    List<RespuestaEstudiante> findAll();

    Optional<RespuestaEstudiante> findById(Long id);

    RespuestaEstudiante save(RespuestaEstudiante respuestaEstudiante);

    Optional<RespuestaEstudiante> delete(RespuestaEstudiante respuestaEstudiante);

    List<RespuestaEstudiante> findByEstudianteId(Long estudianteId);
    
}