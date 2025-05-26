package com.edutech.Soporte.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.Soporte.entities.Ticket;
import com.edutech.Soporte.repository.TicketRepository;



@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repository;

     @Override
     @Transactional (readOnly = true)
    public List<Ticket> findByAll() {
        return (List<Ticket>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findByTituloContainingIgnoreCase(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Ticket> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    @Transactional
    public Ticket save(Ticket unTicket) {
        return repository.save(unTicket);
    }

    @Override
    @Transactional
    public Optional<Ticket> delete(Ticket unTicket) {
        Optional<Ticket> ticketOpcional = repository.findById(unTicket.getId());
        ticketOpcional.ifPresent(ticketDb -> {
            repository.delete(unTicket);
        });
        return ticketOpcional;
    }

}
