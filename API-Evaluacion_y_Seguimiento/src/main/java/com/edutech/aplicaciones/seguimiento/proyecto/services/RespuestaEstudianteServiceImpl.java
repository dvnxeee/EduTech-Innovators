package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.RespuestaEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.RespuestaEstudianteRepository;

@Service
public class RespuestaEstudianteServiceImpl implements RespuestaEstudianteService {

    @Autowired
    private RespuestaEstudianteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<RespuestaEstudiante> findAll() {
        return (List<RespuestaEstudiante>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RespuestaEstudiante> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public RespuestaEstudiante save(RespuestaEstudiante respuestaEstudiante) {
        return repository.save(respuestaEstudiante);
    }

    @Override
    @Transactional
    public Optional<RespuestaEstudiante> delete(RespuestaEstudiante unaRespuesta) {
        Optional<RespuestaEstudiante> respuestaOpcional = repository.findById(unaRespuesta.getId());
        respuestaOpcional.ifPresent(db_proyecto -> {
            repository.delete(unaRespuesta);
        });
        return respuestaOpcional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RespuestaEstudiante> findByEstudianteId(Long estudianteId) {
        return repository.findByEstudianteId(estudianteId);
    }
    
}