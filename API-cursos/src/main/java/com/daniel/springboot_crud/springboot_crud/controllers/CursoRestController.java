package com.daniel.springboot_crud.springboot_crud.controllers;

import com.daniel.springboot_crud.springboot_crud.entities.Curso;
import com.daniel.springboot_crud.springboot_crud.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documentado/cursos")
public class CursoRestController {

    @Autowired
    private CursoService service;

    @Operation(summary = "Listar todos los cursos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de cursos recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Curso.class)),
                examples = @ExampleObject(value = "[{\"id\":1,\"nombreCurso\":\"Java Básico\",\"descripcion\":\"Intro al lenguaje Java\",\"instructor\":\"Daniel\",\"cursoPublicado\":true,\"reporteInscripcion\":\"excel-2025\"}]")
            )
        ),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping
    public List<Curso> listar() {
        return service.findByAll();
    }

    @Operation(summary = "Obtener un curso por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Curso> cursoOptional = service.findById(id);
        return cursoOptional.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo curso")
    @ApiResponse(responseCode = "201", description = "Curso creado exitosamente")
    @PostMapping
    public ResponseEntity<Curso> crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo curso",
            required = true,
            content = @Content(schema = @Schema(implementation = Curso.class))
        )
        @RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));
    }

    @Operation(summary = "Actualizar un curso existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Curso curso) {
        Optional<Curso> cursoOptional = service.findById(id);
        if (cursoOptional.isPresent()) {
            Curso existente = cursoOptional.get();
            existente.setNombreCurso(curso.getNombreCurso());
            existente.setDescripcion(curso.getDescripcion());
            existente.setInstructor(curso.getInstructor());
            existente.setCursoPublicado(curso.isCursoPublicado());
            existente.setReporteInscripcion(curso.getReporteInscripcion());
            return ResponseEntity.ok(service.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un curso por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso eliminado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Curso curso = new Curso();
        curso.setId(id);
        Optional<Curso> cursoOptional = service.delete(curso);
        return cursoOptional.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
