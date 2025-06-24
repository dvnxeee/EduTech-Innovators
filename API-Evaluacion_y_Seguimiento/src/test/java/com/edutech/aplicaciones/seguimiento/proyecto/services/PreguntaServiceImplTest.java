package com.edutech.aplicaciones.seguimiento.proyecto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Pregunta;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PreguntaServiceImplTest {

    @Mock
    private PreguntaRepository repository;

    @InjectMocks
    private PreguntaServiceImpl service;

    private Pregunta pregunta;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        pregunta = new Pregunta(1L, "¿Cuál es la capital de Chile?", "seleccion", "Santiago", 100L);
    }

    @Test
    void listar() {
        when(repository.findAll()).thenReturn(List.of(pregunta));
        var resultado = service.findAll();
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(pregunta));
        var resultado = service.findById(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void guardar() {
        when(repository.save(pregunta)).thenReturn(pregunta);
        var resultado = service.save(pregunta);
        assertNotNull(resultado);
    }

    @Test
    void eliminar() {
        when(repository.findById(1L)).thenReturn(Optional.of(pregunta));
        var resultado = service.delete(pregunta);
        assertTrue(resultado.isPresent());
        verify(repository).delete(pregunta);
    }

    @Test
    void buscarPorEvaluacion() {
        when(repository.findByEvaluacionId(100L)).thenReturn(List.of(pregunta));
        var resultado = service.findByEvaluacionId(100L);
        assertEquals(1, resultado.size());
    }
    
}