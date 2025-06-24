package com.edutech.Soporte.controllers;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import com.edutech.Soporte.entities.Ticket;
import com.edutech.Soporte.services.TicketService;

@RestController
@RequestMapping("/api/soporte")
@Tag(name = "Tickets", description = "Operaciones sobre tickets de soporte")
public class TicketController {

    @Autowired
    private TicketService service;

    @Operation(summary = "Listar todos los tickets")
    @GetMapping
    public List<Ticket> list() {
        return service.findByAll();
    }

    @Operation(summary = "Obtener un ticket por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket encontrado"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Ticket> ticketOptional = service.findById(id);
        if (ticketOptional.isPresent()) {
            return ResponseEntity.ok(ticketOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo ticket")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Ticket creado")
    })
    @PostMapping
    public ResponseEntity<Ticket> crear(@RequestBody Ticket unTicket) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unTicket));
    }

    @Operation(summary = "Actualizar un ticket existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket actualizado"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
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

    @Operation(summary = "Eliminar un ticket")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket eliminado"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
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
