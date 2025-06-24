package com.edutech.Aplicaciones.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.Aplicaciones.entities.Curso;
import com.edutech.Aplicaciones.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    private CursoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findByAll() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso unCurso) {
        return repository.save(unCurso);
    }


    @Override 
    @Transactional 
    public Optional<Curso> delete(Curso unCurso) { 
        Optional<Curso> cursoOptional = repository.findById(unCurso.getId()); 
        cursoOptional.ifPresent(cursoDb -> { 
            
            repository.delete(unCurso);
         });
          return cursoOptional; 
        }
    }
