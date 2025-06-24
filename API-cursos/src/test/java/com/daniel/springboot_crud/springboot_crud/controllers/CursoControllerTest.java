package com.daniel.springboot_crud.springboot_crud.controllers;

import com.daniel.springboot_crud.springboot_crud.entities.Curso;
import com.daniel.springboot_crud.springboot_crud.services.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Curso curso;

    @BeforeEach
    void setUp() {
        curso = new Curso(1L, "Java Básico", "Intro a Java", "Daniel", true, "50 inscritos");
    }

    @Test
    @DisplayName("GET /api/cursos - listar cursos")
    void testListarCursos() throws Exception {
        when(cursoService.findByAll()).thenReturn(Arrays.asList(curso));
        mockMvc.perform(get("/api/cursos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombreCurso").value("Java Básico"));
    }

    @Test
    @DisplayName("GET /api/cursos/1 - detalle")
    void testVerDetalle() throws Exception {
        when(cursoService.findById(1L)).thenReturn(Optional.of(curso));
        mockMvc.perform(get("/api/cursos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.instructor").value("Daniel"));
    }

    @Test
    @DisplayName("POST /api/cursos - crear curso")
    void testCrearCurso() throws Exception {
        when(cursoService.save(any(Curso.class))).thenReturn(curso);
        mockMvc.perform(post("/api/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.nombreCurso").value("Java Básico"));
    }

    @Test
    @DisplayName("PUT /api/cursos/1 - actualizar curso")
    void testActualizarCurso() throws Exception {
        when(cursoService.findById(1L)).thenReturn(Optional.of(curso));
        when(cursoService.save(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(put("/api/cursos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.descripcion").value("Intro a Java"));
    }

    @Test
    @DisplayName("DELETE /api/cursos/1 - eliminar curso")
    void testEliminarCurso() throws Exception {
        when(cursoService.delete(any(Curso.class))).thenReturn(Optional.of(curso));

        mockMvc.perform(delete("/api/cursos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreCurso").value("Java Básico"));
    }
}
