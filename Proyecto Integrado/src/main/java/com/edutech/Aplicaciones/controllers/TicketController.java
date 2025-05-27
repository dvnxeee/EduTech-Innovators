package com.edutech.Aplicaciones.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.Aplicaciones.entities.Ticket;
import com.edutech.Aplicaciones.services.TicketService;

@RestController
@RequestMapping("/api/soporte")
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping
    public List<Ticket> list() {
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Ticket> ticketOptional = service.findById(id);
        if (ticketOptional.isPresent()) {
            return ResponseEntity.ok(ticketOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Ticket> crear(@RequestBody Ticket unTicket) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unTicket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Ticket unTicket) {
        Optional<Ticket> ticketOpcional = service.findById(id);
        if (ticketOpcional.isPresent()) {
            Ticket ticketExistente = ticketOpcional.get();
            ticketExistente.setTitulo(unTicket.getTitulo());
            ticketExistente.setDescripcion(unTicket.getDescripcion());
            ticketExistente.setEstado(unTicket.getEstado());
            ticketExistente.setPrioridad(unTicket.getPrioridad());
            ticketExistente.setTipo(unTicket.getTipo());
            Ticket ticketModificado = service.save(ticketExistente);
            return ResponseEntity.ok(ticketModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Ticket unTicket = new Ticket();
        unTicket.setId(id);
        Optional<Ticket> ticketOpcional = service.delete(unTicket);
        if (ticketOpcional.isPresent()) {
            return ResponseEntity.ok(ticketOpcional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
