package com.edutech.Aplicaciones.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edutech.Aplicaciones.entities.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByTituloContainingIgnoreCase(String titulo);
}
