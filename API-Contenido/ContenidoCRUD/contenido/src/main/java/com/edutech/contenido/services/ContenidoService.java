package com.edutech.contenido.services;

import com.edutech.contenido.entities.Contenido;

import java.util.List;
import java.util.Optional;

public interface ContenidoService {
    List<Contenido> findByAll();

    Optional<Contenido> findById(Long id);

    Contenido save(Contenido contenido);

    Optional<Contenido> delete(Contenido contenido);
}
