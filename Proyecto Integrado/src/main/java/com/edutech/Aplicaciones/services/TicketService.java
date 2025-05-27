package com.edutech.Aplicaciones.services;

import java.util.List;
import java.util.Optional;

import com.edutech.Aplicaciones.entities.Ticket;

public interface TicketService {

    List <Ticket> findByAll();

    Optional<Ticket> findById(Long id);

    Ticket save(Ticket unTicket);

    Optional<Ticket> delete(Ticket unTicket);

    List<Ticket> findByTituloContainingIgnoreCase(String titulo);
}
