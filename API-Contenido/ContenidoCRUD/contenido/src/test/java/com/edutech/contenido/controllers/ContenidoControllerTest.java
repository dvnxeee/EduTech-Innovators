package com.edutech.contenido.controllers;

import com.edutech.contenido.entities.Contenido;
import com.edutech.contenido.services.ContenidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContenidoController.class)
public class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContenidoService contenidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Contenido contenido;

    @BeforeEach
    public void setUp() {
        contenido = new Contenido(
                1L, "CUR001", "Introducción Java", "video",
                "http://example.com/java", true, true, "2025-06-20", "java,basico"
        );
    }

    @Test
    @DisplayName("GET /api/contenidos - debe retornar lista de contenidos")
    public void testListarContenidos() throws Exception {
        when(contenidoService.findByAll()).thenReturn(Arrays.asList(contenido));

        mockMvc.perform(get("/api/contenidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreContenido").value("Introducción Java"));
    }

    @Test
    @DisplayName("GET /api/contenidos/1 - debe retornar un contenido por ID")
    public void testVerDetalleContenido() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(Optional.of(contenido));

        mockMvc.perform(get("/api/contenidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCurso").value("CUR001"))
                .andExpect(jsonPath("$.subido").value(true));
    }

    @Test
    @DisplayName("POST /api/contenidos - debe crear un nuevo contenido")
    public void testCrearContenido() throws Exception {
        when(contenidoService.save(any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(post("/api/contenidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreContenido").value("Introducción Java"));
    }

    @Test
    @DisplayName("PUT /api/contenidos/1 - debe actualizar un contenido existente")
    public void testModificarContenido() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(Optional.of(contenido));
        when(contenidoService.save(any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(put("/api/contenidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("http://example.com/java"));
    }

    @Test
    @DisplayName("DELETE /api/contenidos/1 - debe eliminar contenido")
    public void testEliminarContenido() throws Exception {
        when(contenidoService.delete(any(Contenido.class))).thenReturn(Optional.of(contenido));

        mockMvc.perform(delete("/api/contenidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreContenido").value("Introducción Java"));
    }

    @Test
    @DisplayName("GET /api/contenidos/99 - debe retornar 404 si no existe")
    public void testContenidoNoEncontrado() throws Exception {
        when(contenidoService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/contenidos/99"))
                .andExpect(status().isNotFound());
    }
}

