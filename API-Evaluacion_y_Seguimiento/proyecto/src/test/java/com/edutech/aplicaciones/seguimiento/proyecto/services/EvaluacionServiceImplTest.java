package com.edutech.aplicaciones.seguimiento.proyecto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Evaluacion;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.EvaluacionRepository;

public class EvaluacionServiceImplTest {

    @Mock
    private EvaluacionRepository repository;

    @InjectMocks
    private EvaluacionServiceImpl service;

    private Evaluacion evaluacion1;
    private Evaluacion evaluacion2;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        evaluacion1 = new Evaluacion(1L, "Evaluación 1", "Descripción 1", "2024-01-01");
        evaluacion2 = new Evaluacion(2L, "Evaluación 2", "Descripción 2", "2024-02-01");
    }

    @Test
    void listar() {
        when(repository.findAll()).thenReturn(List.of(evaluacion1, evaluacion2));
        var resultado = service.findAll();
        assertEquals(2, resultado.size());
    }

    @Test
    void buscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(evaluacion1));
        var resultado = service.findById(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void guardar() {
        when(repository.save(evaluacion1)).thenReturn(evaluacion1);
        var resultado = service.save(evaluacion1);
        assertNotNull(resultado);
    }

    @Test
    void eliminarExistente() {
        when(repository.findById(1L)).thenReturn(Optional.of(evaluacion1));
        var resultado = service.delete(evaluacion1);
        assertTrue(resultado.isPresent());
        verify(repository).delete(evaluacion1);
    }

    @Test
    void eliminarInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        var resultado = service.delete(new Evaluacion(99L, "", "", ""));
        assertTrue(resultado.isEmpty());
    }
    
}