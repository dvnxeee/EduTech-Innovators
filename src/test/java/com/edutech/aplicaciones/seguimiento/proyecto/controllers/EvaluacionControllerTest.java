package com.edutech.aplicaciones.seguimiento.proyecto.controllers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Evaluacion;
import com.edutech.aplicaciones.seguimiento.proyecto.services.EvaluacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EvaluacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EvaluacionService evaluacionService;

    @Test
    public void listarEvaluacionesTest() throws Exception {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1L);
        evaluacion.setTitulo("Evaluación Diagnóstica");
        evaluacion.setDescripcion("Prueba inicial");
        evaluacion.setFechaPublicacion("2024-06-20");

        when(evaluacionService.findAll()).thenReturn(Arrays.asList(evaluacion));

        mockMvc.perform(get("/api/evaluaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verEvaluacionExistenteTest() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(2L);
        evaluacion.setTitulo("Final");
        evaluacion.setDescripcion("Evaluación final");
        evaluacion.setFechaPublicacion("2024-06-25");

        try {
            when(evaluacionService.findById(2L)).thenReturn(Optional.of(evaluacion));

            mockMvc.perform(get("/api/evaluaciones/2")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El test lanzó una excepción: " + ex.getMessage());
        }
    }

    @Test
    public void verEvaluacionNoExisteTest() throws Exception {
        when(evaluacionService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/evaluaciones/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearEvaluacionTest() throws Exception {
        Evaluacion nueva = new Evaluacion();
        nueva.setTitulo("Evaluación Intermedia");
        nueva.setDescripcion("Evaluación de mitad de curso");
        nueva.setFechaPublicacion("2024-07-01");

        Evaluacion guardada = new Evaluacion();
        guardada.setId(3L);
        guardada.setTitulo("Evaluación Intermedia");
        guardada.setDescripcion("Evaluación de mitad de curso");
        guardada.setFechaPublicacion("2024-07-01");

        when(evaluacionService.save(any(Evaluacion.class))).thenReturn(guardada);

        mockMvc.perform(post("/api/evaluaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated());
    }

    @Test
    public void eliminarEvaluacionExistenteTest() throws Exception {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(4L);

        when(evaluacionService.delete(any(Evaluacion.class))).thenReturn(Optional.of(evaluacion));

        mockMvc.perform(delete("/api/evaluaciones/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarEvaluacionNoExisteTest() throws Exception {
        when(evaluacionService.delete(any(Evaluacion.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/evaluaciones/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}