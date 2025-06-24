package com.edutech.contenido.controllers;

import com.edutech.contenido.entities.Contenido;
import com.edutech.contenido.services.ContenidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documentado/contenidos")
public class ContenidoRestController {

    @Autowired
    private ContenidoService service;

    @Operation(summary = "Listar todos los contenidos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de contenidos recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Contenido.class)),
                examples = @ExampleObject(value = "[{\"idContenido\":1,\"idCurso\":\"CUR001\",\"nombreContenido\":\"Introducción Java\",\"tipoContenido\":\"video\",\"url\":\"http://example.com/java\",\"subido\":true,\"accesoAlumno\":true,\"fechaUltimaSubidaContenido\":\"2025-06-20\",\"etiquetas\":\"java,basico\"}]")
            )
        ),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping
    public List<Contenido> listar() {
        return service.findByAll();
    }

    @Operation(summary = "Obtener un contenido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido encontrado"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Contenido> contenidoOptional = service.findById(id);
        return contenidoOptional.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo contenido")
    @ApiResponse(responseCode = "201", description = "Contenido creado exitosamente")
    @PostMapping
    public ResponseEntity<Contenido> crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo contenido",
            required = true,
            content = @Content(schema = @Schema(implementation = Contenido.class))
        )
        @RequestBody Contenido contenido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(contenido));
    }

    @Operation(summary = "Actualizar un contenido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido actualizado"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Contenido contenido) {
        Optional<Contenido> contenidoOptional = service.findById(id);
        if (contenidoOptional.isPresent()) {
            Contenido existente = contenidoOptional.get();
            existente.setNombreContenido(contenido.getNombreContenido());
            existente.setTipoContenido(contenido.getTipoContenido());
            existente.setIdCurso(contenido.getIdCurso());
            existente.setUrl(contenido.getUrl());
            existente.setSubido(contenido.getSubido());
            existente.setAccesoAlumno(contenido.getAccesoAlumno());
            existente.setFechaUltimaSubidaContenido(contenido.getFechaUltimaSubidaContenido());
            existente.setEtiquetas(contenido.getEtiquetas());
            return ResponseEntity.ok(service.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un contenido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido eliminado"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Contenido contenido = new Contenido();
        contenido.setIdContenido(id);
        Optional<Contenido> contenidoOptional = service.delete(contenido);
        return contenidoOptional.map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
