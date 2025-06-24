package com.edutech.aplicaciones.seguimiento.proyecto.restcontrollers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.RespuestaEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.services.RespuestaEstudianteService;

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

@Tag(name = "Respuestas de Estudiantes", description = "Operaciones sobre respuestas entregadas por los estudiantes")
@RestController
@RequestMapping("/api/respuestas-estudiante")
public class RespuestaEstudianteController {

    @Autowired
    private RespuestaEstudianteService service;

    @Operation(summary = "Listar todas las respuestas de estudiantes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public List<RespuestaEstudiante> listar() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una respuesta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Respuesta encontrada"),
        @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<RespuestaEstudiante> respuestaOptional = service.findById(id);
        return respuestaOptional.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva respuesta de estudiante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Respuesta creada correctamente")
    })
    @PostMapping
    public ResponseEntity<RespuestaEstudiante> crear(@RequestBody RespuestaEstudiante unaRespuesta) {
        RespuestaEstudiante creada = service.save(unaRespuesta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @Operation(summary = "Actualizar una respuesta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Respuesta actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody RespuestaEstudiante unaRespuesta) {
        Optional<RespuestaEstudiante> respuestaOptional = service.findById(id);
        if (respuestaOptional.isPresent()) {
            RespuestaEstudiante existente = respuestaOptional.get();

            existente.setEstudianteId(unaRespuesta.getEstudianteId());
            existente.setPreguntaId(unaRespuesta.getPreguntaId());
            existente.setRespuesta(unaRespuesta.getRespuesta());
            existente.setEsCorrecta(unaRespuesta.isEsCorrecta());
            existente.setFechaRespuesta(unaRespuesta.getFechaRespuesta());

            RespuestaEstudiante actualizada = service.save(existente);
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una respuesta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Respuesta eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        RespuestaEstudiante unaRespuesta = new RespuestaEstudiante();
        unaRespuesta.setId(id);
        Optional<RespuestaEstudiante> respuestaOptional = service.delete(unaRespuesta);
        return respuestaOptional.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}