package com.edutech.aplicaciones.seguimiento.proyecto.controllers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.RespuestaEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.services.RespuestaEstudianteService;
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
public class RespuestaEstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RespuestaEstudianteService respuestaService;

    @Test
    public void listarRespuestasTest() throws Exception {
        RespuestaEstudiante respuesta = new RespuestaEstudiante();
        respuesta.setId(1L);
        respuesta.setEstudianteId(100L);
        respuesta.setPreguntaId(200L);
        respuesta.setRespuesta("Santiago");
        respuesta.setEsCorrecta(true);
        respuesta.setFechaRespuesta("2024-06-23");

        when(respuestaService.findAll()).thenReturn(Arrays.asList(respuesta));

        mockMvc.perform(get("/api/respuestas-estudiante")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verRespuestaExistenteTest() {
        RespuestaEstudiante respuesta = new RespuestaEstudiante();
        respuesta.setId(2L);
        respuesta.setEstudianteId(101L);
        respuesta.setPreguntaId(201L);
        respuesta.setRespuesta("Azul");
        respuesta.setEsCorrecta(false);
        respuesta.setFechaRespuesta("2024-06-10");

        try {
            when(respuestaService.findById(2L)).thenReturn(Optional.of(respuesta));

            mockMvc.perform(get("/api/respuestas-estudiante/2")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El test lanzó un error: " + ex.getMessage());
        }
    }

    @Test
    public void verRespuestaNoExisteTest() throws Exception {
        when(respuestaService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/respuestas-estudiante/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearRespuestaTest() throws Exception {
        RespuestaEstudiante nueva = new RespuestaEstudiante();
        nueva.setEstudianteId(102L);
        nueva.setPreguntaId(202L);
        nueva.setRespuesta("5");
        nueva.setEsCorrecta(true);
        nueva.setFechaRespuesta("2024-06-24");

        RespuestaEstudiante guardada = new RespuestaEstudiante();
        guardada.setId(3L);
        guardada.setEstudianteId(102L);
        guardada.setPreguntaId(202L);
        guardada.setRespuesta("5");
        guardada.setEsCorrecta(true);
        guardada.setFechaRespuesta("2024-06-24");

        when(respuestaService.save(any(RespuestaEstudiante.class))).thenReturn(guardada);

        mockMvc.perform(post("/api/respuestas-estudiante")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated());
    }

    @Test
    public void eliminarRespuestaExistenteTest() throws Exception {
        RespuestaEstudiante respuesta = new RespuestaEstudiante();
        respuesta.setId(4L);

        when(respuestaService.delete(any(RespuestaEstudiante.class))).thenReturn(Optional.of(respuesta));

        mockMvc.perform(delete("/api/respuestas-estudiante/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarRespuestaNoExisteTest() throws Exception {
        when(respuestaService.delete(any(RespuestaEstudiante.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/respuestas-estudiante/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}