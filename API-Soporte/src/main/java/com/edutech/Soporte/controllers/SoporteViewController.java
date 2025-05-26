package com.edutech.Soporte.controllers;

import com.edutech.Soporte.entities.Ticket;
import com.edutech.Soporte.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class SoporteViewController {

    @Autowired
    private TicketService service;

    // Vista para ingresar un nuevo ticket
    @GetMapping("/nuevo")
    public String nuevoTicket(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "tickets/nuevo-ticket";
    }

    @PostMapping("/guardar")
    public String guardarTicket(@ModelAttribute Ticket ticket, RedirectAttributes redirectAttributes) {
        Ticket savedTicket = service.save(ticket);
        redirectAttributes.addFlashAttribute("mensaje", "¡Ticket creado con éxito! El ID de tu ticket es: " + savedTicket.getId());
        return "redirect:/tickets/nuevo";
    }

    // Vista para editar un ticket
    @GetMapping("/editar/{id}")
    public String editarTicket(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", service.findById(id).orElse(new Ticket()));
        return "tickets/editar-ticket";
    }

    @PostMapping("/actualizar")
    public String actualizarTicket(@ModelAttribute Ticket ticket) {
        service.save(ticket);
        return "redirect:/tickets/buscar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTicket(@PathVariable Long id) {
        service.findById(id).ifPresent(service::delete);
        return "redirect:/tickets/buscar";
    }

    // Vista combinada de búsqueda y lista
    @GetMapping("/buscar")
    public String buscarTicket(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String titulo,
            Model model) {
        List<Ticket> tickets;
        if (id != null) {
            Optional<Ticket> ticket = service.findById(id);
            tickets = ticket.map(List::of).orElse(List.of());
        } else if (titulo != null && !titulo.isEmpty()) {
            tickets = service.findByTituloContainingIgnoreCase(titulo);
        } else {
            tickets = service.findByAll();
        }
        model.addAttribute("tickets", tickets);
        return "tickets/buscar-lista-tickets";
    }
}