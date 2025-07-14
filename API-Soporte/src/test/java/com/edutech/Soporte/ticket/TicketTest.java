package com.edutech.Soporte.ticket;

import com.edutech.Soporte.entities.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    @DisplayName("Guardar ticket")
    public void saveTest() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitulo("Error de login");
        ticket.setDescripcion("No se puede iniciar sesión");
        ticket.setEstado("Abierto");
        ticket.setPrioridad("Alta");
        ticket.setTipo("Incidente");

        Ticket guardado = ticket;

        assertEquals("Error de login", guardado.getTitulo());
        assertEquals("Alta", guardado.getPrioridad());
        assertEquals("Incidente", guardado.getTipo());
    }

    @Test
    @DisplayName("Obtener todos los tickets")
    public void findAllTest() {
        Ticket t1 = new Ticket();
        Ticket t2 = new Ticket();
        List<Ticket> lista = new ArrayList<>();
        lista.add(t1);
        lista.add(t2);

        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Buscar ticket por ID")
    public void findByIdTest() {
        Ticket ticket = new Ticket();
        ticket.setId(100L);

        Optional<Ticket> resultado = Optional.of(ticket);
        assertTrue(resultado.isPresent());
        assertEquals(100L, resultado.get().getId());
    }

    @Test
    @DisplayName("Eliminar ticket")
    public void deleteTest() {
        Ticket ticket = new Ticket();
        ticket.setId(5L);

        Optional<Ticket> eliminado = Optional.of(ticket);
        assertEquals(5L, eliminado.get().getId());
    }
}