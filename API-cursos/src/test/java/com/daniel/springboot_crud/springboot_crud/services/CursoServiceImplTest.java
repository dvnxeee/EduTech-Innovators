package com.daniel.springboot_crud.springboot_crud.services;

import com.daniel.springboot_crud.springboot_crud.entities.Curso;
import com.daniel.springboot_crud.springboot_crud.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoServiceImplTest {

    @InjectMocks
    private CursoServiceImpl cursoService;

    @Mock
    private CursoRepository cursoRepository;

    List<Curso> lista = new ArrayList<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cargarCursos();
    }

    private void cargarCursos() {
        Curso curso1 = new Curso(1L, "Java Básico", "Curso de introducción a Java", "Daniel", true, "50 inscritos");
        Curso curso2 = new Curso(2L, "Spring Boot", "Aprende a crear APIs", "Laura", false, "30 inscritos");
        lista.add(curso1);
        lista.add(curso2);
    }

    @Test
    @DisplayName("Listar todos los cursos")
    public void testFindAll() {
        when(cursoRepository.findAll()).thenReturn(lista);
        List<Curso> result = cursoService.findByAll();
        assertEquals(2, result.size());
        verify(cursoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar curso por ID")
    public void testFindById() {
        Curso curso = lista.get(0);
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        Optional<Curso> result = cursoService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Java Básico", result.get().getNombreCurso());
    }

    @Test
    @DisplayName("Guardar curso")
    public void testSave() {
        Curso nuevo = new Curso(3L, "Docker", "Contenedores", "Carlos", true, "10 inscritos");
        when(cursoRepository.save(nuevo)).thenReturn(nuevo);
        Curso result = cursoService.save(nuevo);
        assertEquals("Docker", result.getNombreCurso());
    }

    @Test
    @DisplayName("Eliminar curso existente")
    public void testDelete() {
        Curso curso = lista.get(0);
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        Optional<Curso> deleted = cursoService.delete(curso);
        assertTrue(deleted.isPresent());
        verify(cursoRepository, times(1)).delete(curso);
    }

    @Test
    @DisplayName("Eliminar curso inexistente")
    public void testDeleteNotFound() {
        Curso curso = new Curso();
        curso.setId(100L);
        when(cursoRepository.findById(100L)).thenReturn(Optional.empty());
        Optional<Curso> result = cursoService.delete(curso);
        assertFalse(result.isPresent());
        verify(cursoRepository, never()).delete(any());
    }
}
