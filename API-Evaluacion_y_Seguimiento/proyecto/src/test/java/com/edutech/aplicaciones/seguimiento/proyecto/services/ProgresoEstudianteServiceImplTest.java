package com.edutech.aplicaciones.seguimiento.proyecto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.ProgresoEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.ProgresoEstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProgresoEstudianteServiceImplTest {

    @Mock
    private ProgresoEstudianteRepository repository;

    @InjectMocks
    private ProgresoEstudianteServiceImpl service;

    private ProgresoEstudiante progreso;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        progreso = new ProgresoEstudiante(1L, 10L, 20L, 80.0, 6.5);
    }

    @Test
    void listar() {
        when(repository.findAll()).thenReturn(List.of(progreso));
        var resultado = service.findAll();
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(progreso));
        var resultado = service.findById(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void guardar() {
        when(repository.save(progreso)).thenReturn(progreso);
        var resultado = service.save(progreso);
        assertNotNull(resultado);
    }

    @Test
    void eliminar() {
        when(repository.findById(1L)).thenReturn(Optional.of(progreso));
        var resultado = service.delete(progreso);
        assertTrue(resultado.isPresent());
        verify(repository).delete(progreso);
    }

    @Test
    void buscarPorEstudianteYCurso() {
        when(repository.findByEstudianteIdAndCursoId(10L, 20L)).thenReturn(Optional.of(progreso));
        var resultado = service.findByEstudianteIdAndCursoId(10L, 20L);
        assertTrue(resultado.isPresent());
    }
    
}