package com.edutech.contenido.services;

import com.edutech.contenido.entities.Contenido;
import com.edutech.contenido.repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoServiceImpl implements ContenidoService {

    @Autowired
    private ContenidoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Contenido> findByAll() {
        return (List<Contenido>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contenido> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Contenido save(Contenido contenido) {
        return repository.save(contenido);
    }

    @Override
    @Transactional
    public Optional<Contenido> delete(Contenido contenido) {
        Optional<Contenido> contenidoOptional = repository.findById(contenido.getIdContenido());
        contenidoOptional.ifPresent(repository::delete);
        return contenidoOptional;
    }
}

