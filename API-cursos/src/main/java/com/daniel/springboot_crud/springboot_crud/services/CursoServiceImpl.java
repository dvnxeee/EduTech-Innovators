package com.daniel.springboot_crud.springboot_crud.services;

import com.daniel.springboot_crud.springboot_crud.entities.Curso;
import com.daniel.springboot_crud.springboot_crud.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

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
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public Optional<Curso> delete(Curso curso) {
        Optional<Curso> cursoOptional = repository.findById(curso.getId());
        cursoOptional.ifPresent(repository::delete);
        return cursoOptional;
    }
}
