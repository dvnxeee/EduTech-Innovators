package com.edutech.Aplicaciones.services;

import java.util.List;
import java.util.Optional;

import com.edutech.Aplicaciones.entities.Curso;

public interface CursoService {
    List <Curso> findByAll();

    Optional<Curso> findById(Long id);

    Curso save(Curso unCurso);

    Optional<Curso> delete(Curso unCurso);

}
