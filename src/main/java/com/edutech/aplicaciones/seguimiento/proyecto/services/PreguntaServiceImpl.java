package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Pregunta;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.PreguntaRepository;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Pregunta> findAll() {
        return (List<Pregunta>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pregunta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Pregunta save(Pregunta pregunta) {
        return repository.save(pregunta);
    }

    @Override
    @Transactional
    public Optional<Pregunta> delete(Pregunta unaPregunta) {
        Optional<Pregunta> preguntaOpcional = repository.findById(unaPregunta.getId());
        preguntaOpcional.ifPresent(db_proyecto -> {
            repository.delete(unaPregunta);
        });
        return preguntaOpcional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pregunta> findByEvaluacionId(Long evaluacionId) {
        return repository.findByEvaluacionId(evaluacionId);
    }

}