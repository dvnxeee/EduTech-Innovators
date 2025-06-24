package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.ProgresoEstudiante;

public interface ProgresoEstudianteService {

    List<ProgresoEstudiante> findAll();

    Optional<ProgresoEstudiante> findById(Long id);

    ProgresoEstudiante save(ProgresoEstudiante progresoEstudiante);

    Optional<ProgresoEstudiante> delete(ProgresoEstudiante progresoEstudiante);

    Optional<ProgresoEstudiante> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);

}