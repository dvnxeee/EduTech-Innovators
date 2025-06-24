package com.edutech.contenido.controllers;

import com.edutech.contenido.entities.Contenido;
import com.edutech.contenido.services.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService service;

    @GetMapping
    public List<Contenido> listar() {
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Contenido> contenidoOptional = service.findById(id);
        if (contenidoOptional.isPresent()) {
            return ResponseEntity.ok(contenidoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Contenido> crear(@RequestBody Contenido contenido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(contenido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Contenido contenido) {
        Optional<Contenido> contenidoOptional = service.findById(id);
        if (contenidoOptional.isPresent()) {
            Contenido contenidoExistente = contenidoOptional.get();
            contenidoExistente.setIdCurso(contenido.getIdCurso());
            contenidoExistente.setNombreContenido(contenido.getNombreContenido());
            contenidoExistente.setTipoContenido(contenido.getTipoContenido());
            contenidoExistente.setUrl(contenido.getUrl());
            contenidoExistente.setSubido(contenido.getSubido());
            contenidoExistente.setAccesoAlumno(contenido.getAccesoAlumno());
            contenidoExistente.setFechaUltimaSubidaContenido(contenido.getFechaUltimaSubidaContenido());
            contenidoExistente.setEtiquetas(contenido.getEtiquetas());
            return ResponseEntity.ok(service.save(contenidoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Contenido contenido = new Contenido();
        contenido.setIdContenido(id);
        Optional<Contenido> contenidoOptional = service.delete(contenido);
        if (contenidoOptional.isPresent()) {
            return ResponseEntity.ok(contenidoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}

