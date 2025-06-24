package com.edutech.Aplicaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.edutech.Aplicaciones.entities.Curso;
import com.edutech.Aplicaciones.services.CursoService;

@Controller
@RequestMapping("/vista/cursos")
public class CursoViewController {

    @Autowired
    private CursoService service;

    // Mostrar todos los cursos
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", service.findByAll());
        model.addAttribute("curso", new Curso());
        model.addAttribute("editMode", false);
        return "cursos";  // archivo cursos.html
    }

    // Guardar un nuevo curso
    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        service.save(curso);
        return "redirect:/vista/cursos";
    }

    // Editar curso existente
    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        model.addAttribute("curso", service.findById(id).orElse(new Curso()));
        model.addAttribute("cursos", service.findByAll());
        model.addAttribute("editMode", true);
        return "cursos";
    }

    // Actualizar curso editado
    @PostMapping("/actualizar")
    public String actualizarCurso(@ModelAttribute Curso curso) {
        service.save(curso);
        return "redirect:/vista/cursos";
    }

    // Eliminar curso
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        service.findById(id).ifPresent(service::delete);
        return "redirect:/vista/cursos";
    }
}

