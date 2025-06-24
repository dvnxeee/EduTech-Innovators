package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Evaluacion;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.EvaluacionRepository;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    @Autowired
    private EvaluacionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Evaluacion> findAll() {
        return (List<Evaluacion>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Evaluacion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Evaluacion save(Evaluacion evaluacion) {
        return repository.save(evaluacion);
    }

    @Override
    @Transactional
    public Optional<Evaluacion> delete(Evaluacion unaEvaluacion) {
        Optional<Evaluacion> evaluacionOpcional = repository.findById(unaEvaluacion.getId());
        evaluacionOpcional.ifPresent(db_proyecto -> {
            repository.delete(unaEvaluacion);
        });
        return evaluacionOpcional;
    }

}