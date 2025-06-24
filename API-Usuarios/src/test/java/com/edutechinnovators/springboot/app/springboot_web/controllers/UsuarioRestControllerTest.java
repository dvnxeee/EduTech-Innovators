package com.edutechinnovators.springboot.app.springboot_web.controllers;

import com.edutechinnovators.springboot.app.springboot_web.entities.Usuario;
import com.edutechinnovators.springboot.app.springboot_web.services.UsuarioService;
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
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    public void listarUsuariosTest() throws Exception {
        Usuario usuario = new Usuario(1L, "Camila", "Fernández", "camila@duoc.cl", "123", "ADMIN", true);
        when(usuarioService.findAll()).thenReturn(Arrays.asList(usuario));

        mockMvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verDetalleUsuarioTest() {
        Usuario usuario = new Usuario(2L, "Pedro", "Gómez", "pedro@duoc.cl", "pass", "USER", true);
        try {
            when(usuarioService.findById(2L)).thenReturn(Optional.of(usuario));

            mockMvc.perform(get("/api/usuarios/2")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El test lanzó un error: " + ex.getMessage());
        }
    }

    @Test
    public void usuarioNoExisteTest() throws Exception {
        when(usuarioService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuarios/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception {
        Usuario nuevo = new Usuario(null, "Daniela", "Soto", "dani@duoc.cl", "clave", "USER", true);
        Usuario guardado = new Usuario(3L, "Daniela", "Soto", "dani@duoc.cl", "clave", "USER", true);

        when(usuarioService.save(any(Usuario.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated());
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        Usuario usuario = new Usuario(4L, "Ignacio", "Torres", "nacho@duoc.cl", "4321", "USER", true);

        when(usuarioService.delete(any(Usuario.class))).thenReturn(Optional.of(usuario));

        mockMvc.perform(delete("/api/usuarios/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarUsuarioNoExisteTest() throws Exception {
        when(usuarioService.delete(any(Usuario.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/usuarios/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void desactivarUsuarioNoExisteTest() throws Exception {
        when(usuarioService.desactivate(1000L)).thenReturn(null);

        mockMvc.perform(patch("/api/usuarios/1000/desactivar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}