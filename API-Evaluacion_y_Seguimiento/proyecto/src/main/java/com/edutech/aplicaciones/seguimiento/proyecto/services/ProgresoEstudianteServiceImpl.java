package com.edutech.aplicaciones.seguimiento.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.ProgresoEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.ProgresoEstudianteRepository;

@Service
public class ProgresoEstudianteServiceImpl implements ProgresoEstudianteService {

    @Autowired
    private ProgresoEstudianteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ProgresoEstudiante> findAll() {
        return (List<ProgresoEstudiante>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgresoEstudiante> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ProgresoEstudiante save(ProgresoEstudiante progresoEstudiante) {
        return repository.save(progresoEstudiante);
    }

    @Override
    @Transactional
    public Optional<ProgresoEstudiante> delete(ProgresoEstudiante unProgreso) {
        Optional<ProgresoEstudiante> progresoOpcional = repository.findById(unProgreso.getId());
        progresoOpcional.ifPresent(db_proyecto -> {
            repository.delete(unProgreso);
        });
        return progresoOpcional;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgresoEstudiante> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId) {
        return repository.findByEstudianteIdAndCursoId(estudianteId, cursoId);
    }

}