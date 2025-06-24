package com.edutech.Soporte.controllers;

import com.edutech.Soporte.entities.Ticket;
import com.edutech.Soporte.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketService ticketService;

    @Test
    public void listarTicketsTest() throws Exception {
        Ticket ticket = new Ticket(1L, "Titulo", "Descripcion", "Pendiente", "Alta", "Soporte");
        when(ticketService.findByAll()).thenReturn(Arrays.asList(ticket));

        mockMvc.perform(get("/api/soporte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Titulo"));
    }

    @Test
    public void verDetalleTicketTest() throws Exception {
        Ticket ticket = new Ticket(2L, "Titulo2", "Descripcion2", "Resuelto", "Media", "Incidente");
        when(ticketService.findById(2L)).thenReturn(Optional.of(ticket));

        mockMvc.perform(get("/api/soporte/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Titulo2"));
    }

    @Test
    public void verDetalleTicketNoExisteTest() throws Exception {
        when(ticketService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/soporte/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearTicketTest() throws Exception {
        Ticket ticket = new Ticket(null, "Nuevo", "Desc", "Pendiente", "Baja", "Consulta");
        Ticket saved = new Ticket(10L, "Nuevo", "Desc", "Pendiente", "Baja", "Consulta");

        when(ticketService.save(any(Ticket.class))).thenReturn(saved);

        mockMvc.perform(post("/api/soporte")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    public void modificarTicketTest() throws Exception {
        Ticket ticket = new Ticket(5L, "Viejo", "Desc", "Pendiente", "Media", "Soporte");
        Ticket modificado = new Ticket(5L, "Modificado", "DescMod", "Resuelto", "Alta", "Incidente");

        when(ticketService.findById(5L)).thenReturn(Optional.of(ticket));
        when(ticketService.save(any(Ticket.class))).thenReturn(modificado);

        Ticket update = new Ticket(null, "Modificado", "DescMod", "Resuelto", "Alta", "Incidente");

        mockMvc.perform(put("/api/soporte/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Modificado"));
    }

    @Test
    public void modificarTicketNoExisteTest() throws Exception {
        when(ticketService.findById(100L)).thenReturn(Optional.empty());

        Ticket update = new Ticket(null, "Modificado", "DescMod", "Resuelto", "Alta", "Incidente");

        mockMvc.perform(put("/api/soporte/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void eliminarTicketTest() throws Exception {
        Ticket ticket = new Ticket(7L, "Eliminar", "Desc", "Pendiente", "Baja", "Soporte");
        when(ticketService.delete(any(Ticket.class))).thenReturn(Optional.of(ticket));

        mockMvc.perform(delete("/api/soporte/7")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7L));
    }

    @Test
    public void eliminarTicketNoExisteTest() throws Exception {
        when(ticketService.delete(any(Ticket.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/soporte/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}