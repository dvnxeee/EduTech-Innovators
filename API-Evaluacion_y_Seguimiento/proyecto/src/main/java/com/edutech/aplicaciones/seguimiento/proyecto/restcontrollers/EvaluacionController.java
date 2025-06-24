package com.edutech.aplicaciones.seguimiento.proyecto.restcontrollers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Evaluacion;
import com.edutech.aplicaciones.seguimiento.proyecto.services.EvaluacionService;

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

@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con evaluaciones")
@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService service;

    @Operation(summary = "Listar todas las evaluaciones")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluaciones obtenidas correctamente")
    })
    @GetMapping
    public List<Evaluacion> listar() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una evaluación por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Evaluacion> evaluacionOptional = service.findById(id);
        return evaluacionOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva evaluación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Evaluación creada correctamente")
    })
    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion unaEvaluacion) {
        Evaluacion creada = service.save(unaEvaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @Operation(summary = "Actualizar una evaluación existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluación actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Evaluacion unaEvaluacion) {
        Optional<Evaluacion> evaluacionOptional = service.findById(id);
        if (evaluacionOptional.isPresent()) {
            Evaluacion evaluacionExistente = evaluacionOptional.get();
            evaluacionExistente.setTitulo(unaEvaluacion.getTitulo());
            evaluacionExistente.setDescripcion(unaEvaluacion.getDescripcion());
            evaluacionExistente.setFechaPublicacion(unaEvaluacion.getFechaPublicacion());

            Evaluacion actualizada = service.save(evaluacionExistente);
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una evaluación por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluación eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Evaluacion unaEvaluacion = new Evaluacion();
        unaEvaluacion.setId(id);
        Optional<Evaluacion> evaluacionOptional = service.delete(unaEvaluacion);
        return evaluacionOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}