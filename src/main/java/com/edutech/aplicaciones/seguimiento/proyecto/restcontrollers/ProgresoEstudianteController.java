package com.edutech.aplicaciones.seguimiento.proyecto.restcontrollers;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.ProgresoEstudiante;
import com.edutech.aplicaciones.seguimiento.proyecto.services.ProgresoEstudianteService;

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

@Tag(name = "Progreso de Estudiantes", description = "Operaciones sobre el seguimiento académico de los estudiantes")
@RestController
@RequestMapping("/api/progreso-estudiante")
public class ProgresoEstudianteController {

    @Autowired
    private ProgresoEstudianteService service;

    @Operation(summary = "Listar todo el progreso de estudiantes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public List<ProgresoEstudiante> listar() {
        return service.findAll();
    }

    @Operation(summary = "Obtener progreso por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progreso encontrado"),
        @ApiResponse(responseCode = "404", description = "Progreso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<ProgresoEstudiante> progresoOptional = service.findById(id);
        return progresoOptional.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo registro de progreso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Progreso creado correctamente")
    })
    @PostMapping
    public ResponseEntity<ProgresoEstudiante> crear(@RequestBody ProgresoEstudiante unProgreso) {
        ProgresoEstudiante creado = service.save(unProgreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar un registro de progreso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progreso actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Progreso no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody ProgresoEstudiante unProgreso) {
        Optional<ProgresoEstudiante> progresoOptional = service.findById(id);
        if (progresoOptional.isPresent()) {
            ProgresoEstudiante existente = progresoOptional.get();

            existente.setEstudianteId(unProgreso.getEstudianteId());
            existente.setCursoId(unProgreso.getCursoId());
            existente.setPorcentajeCompletado(unProgreso.getPorcentajeCompletado());
            existente.setCalificacionPromedio(unProgreso.getCalificacionPromedio());

            ProgresoEstudiante actualizado = service.save(existente);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un registro de progreso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progreso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Progreso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        ProgresoEstudiante unProgreso = new ProgresoEstudiante();
        unProgreso.setId(id);
        Optional<ProgresoEstudiante> progresoOptional = service.delete(unProgreso);
        return progresoOptional.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
