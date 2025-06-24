package com.edutech.aplicaciones.seguimiento.proyecto.restcontrollers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Pregunta;
import com.edutech.aplicaciones.seguimiento.proyecto.services.PreguntaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Preguntas", description = "Operaciones relacionadas con las preguntas de evaluación")
@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

    @Autowired
    private PreguntaService service;

    @Operation(summary = "Listar todas las preguntas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public List<Pregunta> listar() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una pregunta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pregunta encontrada"),
        @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Pregunta> preguntaOptional = service.findById(id);
        return preguntaOptional.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva pregunta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pregunta creada correctamente")
    })
    @PostMapping
    public ResponseEntity<Pregunta> crear(@RequestBody Pregunta unaPregunta) {
        Pregunta nueva = service.save(unaPregunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @Operation(summary = "Actualizar una pregunta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pregunta modificada correctamente"),
        @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Pregunta unaPregunta) {
        Optional<Pregunta> preguntaOptional = service.findById(id);
        if (preguntaOptional.isPresent()) {
            Pregunta existente = preguntaOptional.get();
            existente.setEnunciado(unaPregunta.getEnunciado());
            existente.setTipo(unaPregunta.getTipo());
            existente.setRespuestaCorrecta(unaPregunta.getRespuestaCorrecta());
            existente.setEvaluacionId(unaPregunta.getEvaluacionId());

            Pregunta actualizada = service.save(existente);
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una pregunta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pregunta eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Pregunta unaPregunta = new Pregunta();
        unaPregunta.setId(id);
        Optional<Pregunta> preguntaOptional = service.delete(unaPregunta);
        return preguntaOptional.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}