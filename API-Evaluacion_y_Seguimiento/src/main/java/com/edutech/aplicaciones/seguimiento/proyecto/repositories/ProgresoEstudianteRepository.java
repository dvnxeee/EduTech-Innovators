package com.edutech.aplicaciones.seguimiento.proyecto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.ProgresoEstudiante;

import java.util.Optional;

public interface ProgresoEstudianteRepository extends CrudRepository<ProgresoEstudiante, Long> {
    Optional<ProgresoEstudiante> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}
