package com.edutech.aplicaciones.seguimiento.proyecto.controllers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Pregunta;
import com.edutech.aplicaciones.seguimiento.proyecto.services.PreguntaService;
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
public class PreguntaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PreguntaService preguntaService;

    @Test
    public void listarPreguntasTest() throws Exception {
        Pregunta pregunta = new Pregunta();
        pregunta.setId(1L);
        pregunta.setEnunciado("¿Cuál es la capital de Chile?");
        pregunta.setTipo("alternativa");
        pregunta.setRespuestaCorrecta("Santiago");
        pregunta.setEvaluacionId(10L);

        when(preguntaService.findAll()).thenReturn(Arrays.asList(pregunta));

        mockMvc.perform(get("/api/preguntas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verPreguntaExistenteTest() {
        Pregunta pregunta = new Pregunta();
        pregunta.setId(2L);
        pregunta.setEnunciado("¿2 + 2?");
        pregunta.setTipo("numerica");
        pregunta.setRespuestaCorrecta("4");
        pregunta.setEvaluacionId(20L);

        try {
            when(preguntaService.findById(2L)).thenReturn(Optional.of(pregunta));

            mockMvc.perform(get("/api/preguntas/2")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El test lanzó un error: " + ex.getMessage());
        }
    }

    @Test
    public void verPreguntaNoExisteTest() throws Exception {
        when(preguntaService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/preguntas/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearPreguntaTest() throws Exception {
        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado("¿Color del cielo?");
        pregunta.setTipo("alternativa");
        pregunta.setRespuestaCorrecta("Azul");
        pregunta.setEvaluacionId(30L);

        Pregunta saved = new Pregunta();
        saved.setId(3L);
        saved.setEnunciado("¿Color del cielo?");
        saved.setTipo("alternativa");
        saved.setRespuestaCorrecta("Azul");
        saved.setEvaluacionId(30L);

        when(preguntaService.save(any(Pregunta.class))).thenReturn(saved);

        mockMvc.perform(post("/api/preguntas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pregunta)))
                .andExpect(status().isCreated());
    }

    @Test
    public void eliminarPreguntaExistenteTest() throws Exception {
        Pregunta pregunta = new Pregunta();
        pregunta.setId(4L);

        when(preguntaService.delete(any(Pregunta.class))).thenReturn(Optional.of(pregunta));

        mockMvc.perform(delete("/api/preguntas/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarPreguntaNoExisteTest() throws Exception {
        when(preguntaService.delete(any(Pregunta.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/preguntas/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
}