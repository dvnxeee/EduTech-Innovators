package com.edutech.aplicaciones.seguimiento.proyecto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.RespuestaEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.repositories.RespuestaEstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RespuestaEstudianteServiceImplTest {

    @Mock
    private RespuestaEstudianteRepository repository;

    @InjectMocks
    private RespuestaEstudianteServiceImpl service;

    private RespuestaEstudiante respuesta;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        respuesta = new RespuestaEstudiante(1L, 10L, 100L, "Santiago", true, "2024-06-01");
    }

    @Test
    void listar() {
        when(repository.findAll()).thenReturn(List.of(respuesta));
        var resultado = service.findAll();
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(respuesta));
        var resultado = service.findById(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void guardar() {
        when(repository.save(respuesta)).thenReturn(respuesta);
        var resultado = service.save(respuesta);
        assertNotNull(resultado);
    }

    @Test
    void eliminar() {
        when(repository.findById(1L)).thenReturn(Optional.of(respuesta));
        var resultado = service.delete(respuesta);
        assertTrue(resultado.isPresent());
        verify(repository).delete(respuesta);
    }

    @Test
    void buscarPorEstudiante() {
        when(repository.findByEstudianteId(10L)).thenReturn(List.of(respuesta));
        var resultado = service.findByEstudianteId(10L);
        assertEquals(1, resultado.size());
    }

}