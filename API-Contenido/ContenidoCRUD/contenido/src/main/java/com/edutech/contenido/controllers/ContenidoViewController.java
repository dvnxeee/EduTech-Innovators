package com.edutech.contenido.controllers;

import com.edutech.contenido.entities.Contenido;
import com.edutech.contenido.services.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/contenidos")
public class ContenidoViewController {

    @Autowired
    private ContenidoService service;

    @GetMapping
    public String listarContenidos(Model model) {
        model.addAttribute("contenidos", service.findByAll());
        model.addAttribute("contenido", new Contenido());
        model.addAttribute("editMode", false);
        return "contenido"; // archivo contenido.html
    }

    @PostMapping("/guardar")
    public String guardarContenido(@ModelAttribute Contenido contenido) {
        service.save(contenido);
        return "redirect:/vista/contenidos";
    }

    @GetMapping("/editar/{id}")
    public String editarContenido(@PathVariable Long id, Model model) {
        model.addAttribute("contenido", service.findById(id).orElse(new Contenido()));
        model.addAttribute("contenidos", service.findByAll());
        model.addAttribute("editMode", true);
        return "contenido";
    }

    @PostMapping("/actualizar")
    public String actualizarContenido(@ModelAttribute Contenido contenido) {
        service.save(contenido);
        return "redirect:/vista/contenidos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarContenido(@PathVariable Long id) {
        service.findById(id).ifPresent(service::delete);
        return "redirect:/vista/contenidos";
    }
}
