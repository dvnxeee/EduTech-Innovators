package com.edutech.aplicaciones.seguimiento.proyecto.controllers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.ProgresoEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.services.ProgresoEstudianteService;
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
public class ProgresoEstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProgresoEstudianteService progresoService;

    @Test
    public void listarProgresoTest() throws Exception {
        ProgresoEstudiante progreso = new ProgresoEstudiante();
        progreso.setId(1L);
        progreso.setEstudianteId(100L);
        progreso.setCursoId(200L);
        progreso.setPorcentajeCompletado(80.0);
        progreso.setCalificacionPromedio(6.5);

        when(progresoService.findAll()).thenReturn(Arrays.asList(progreso));

        mockMvc.perform(get("/api/progreso-estudiante")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verProgresoExistenteTest() {
        ProgresoEstudiante progreso = new ProgresoEstudiante();
        progreso.setId(2L);
        progreso.setEstudianteId(101L);
        progreso.setCursoId(201L);
        progreso.setPorcentajeCompletado(90.0);
        progreso.setCalificacionPromedio(5.8);

        try {
            when(progresoService.findById(2L)).thenReturn(Optional.of(progreso));

            mockMvc.perform(get("/api/progreso-estudiante/2")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("Error al ejecutar el test: " + ex.getMessage());
        }
    }

    @Test
    public void verProgresoNoExisteTest() throws Exception {
        when(progresoService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/progreso-estudiante/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProgresoTest() throws Exception {
        ProgresoEstudiante progreso = new ProgresoEstudiante();
        progreso.setEstudianteId(102L);
        progreso.setCursoId(202L);
        progreso.setPorcentajeCompletado(75.0);
        progreso.setCalificacionPromedio(6.0);

        ProgresoEstudiante guardado = new ProgresoEstudiante();
        guardado.setId(3L);
        guardado.setEstudianteId(102L);
        guardado.setCursoId(202L);
        guardado.setPorcentajeCompletado(75.0);
        guardado.setCalificacionPromedio(6.0);

        when(progresoService.save(any(ProgresoEstudiante.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/progreso-estudiante")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(progreso)))
                .andExpect(status().isCreated());
    }

    @Test
    public void eliminarProgresoExistenteTest() throws Exception {
        ProgresoEstudiante progreso = new ProgresoEstudiante();
        progreso.setId(4L);

        when(progresoService.delete(any(ProgresoEstudiante.class))).thenReturn(Optional.of(progreso));

        mockMvc.perform(delete("/api/progreso-estudiante/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarProgresoNoExisteTest() throws Exception {
        when(progresoService.delete(any(ProgresoEstudiante.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/progreso-estudiante/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
}