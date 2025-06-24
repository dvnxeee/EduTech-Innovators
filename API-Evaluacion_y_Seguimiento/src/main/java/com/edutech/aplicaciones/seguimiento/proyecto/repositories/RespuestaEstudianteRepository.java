package com.edutech.aplicaciones.seguimiento.proyecto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.RespuestaEstudiante;

import java.util.List;

public interface RespuestaEstudianteRepository extends CrudRepository<RespuestaEstudiante, Long> {
    List<RespuestaEstudiante> findByEstudianteId(Long estudianteId);

}