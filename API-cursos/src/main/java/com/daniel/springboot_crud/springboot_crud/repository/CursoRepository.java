package com.daniel.springboot_crud.springboot_crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.daniel.springboot_crud.springboot_crud.entities.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

}
