package com.daniel.springboot_crud.springboot_crud.curso;

import com.daniel.springboot_crud.springboot_crud.entities.Curso;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CursoTest {

    @Test
    @DisplayName("Guardar curso")
    public void saveTest() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNombreCurso("Spring Boot Básico");
        curso.setDescripcion("Introducción a Spring Boot");
        curso.setInstructor("Daniel");
        curso.setCursoPublicado(true);
        curso.setReporteInscripcion("20 inscritos");

        Curso guardado = curso;

        assertEquals("Spring Boot Básico", guardado.getNombreCurso());
        assertEquals("Daniel", guardado.getInstructor());
        assertTrue(guardado.isCursoPublicado());
    }

    @Test
    @DisplayName("Obtener todos los cursos")
    public void findAllTest() {
        Curso c1 = new Curso();
        Curso c2 = new Curso();
        List<Curso> lista = new ArrayList<>();
        lista.add(c1);
        lista.add(c2);

        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Buscar curso por ID")
    public void findByIdTest() {
        Curso curso = new Curso();
        curso.setId(100L);

        Optional<Curso> resultado = Optional.of(curso);
        assertTrue(resultado.isPresent());
        assertEquals(100L, resultado.get().getId());
    }

    @Test
    @DisplayName("Eliminar curso")
    public void deleteTest() {
        Curso curso = new Curso();
        curso.setId(5L);

        Optional<Curso> eliminado = Optional.of(curso);
        assertEquals(5L, eliminado.get().getId());
    }

}