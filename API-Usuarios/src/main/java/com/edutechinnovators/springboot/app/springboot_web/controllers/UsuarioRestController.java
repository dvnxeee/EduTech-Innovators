package com.edutechinnovators.springboot.app.springboot_web.controllers;

import com.edutechinnovators.springboot.app.springboot_web.entities.Usuario;
import com.edutechinnovators.springboot.app.springboot_web.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public List<Usuario> listar() {
        return service.findAll();
    }

    @Operation(summary = "Listar usuarios activos")
    @GetMapping("/activos")
    public List<Usuario> listarActivos() {
        return service.findAllActive();
    }

    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado")
    })
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario unUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario unUsuario) {
        Optional<Usuario> usuarioOptional = service.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario existente = usuarioOptional.get();
            existente.setNombre(unUsuario.getNombre());
            existente.setApellido(unUsuario.getApellido());
            existente.setCorreo(unUsuario.getCorreo());
            if (unUsuario.getContrasenna() != null && !unUsuario.getContrasenna().isEmpty()) {
                existente.setContrasenna(unUsuario.getContrasenna());
            }
            existente.setRol(unUsuario.getRol());
            existente.setActivado(unUsuario.isActivado());
            Usuario actualizado = service.save(existente);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);
        Optional<Usuario> eliminado = service.delete(unUsuario);
        if (eliminado.isPresent()) {
            return ResponseEntity.ok(eliminado.get());
        }
        return ResponseEntity.notFound().build();
    }
}
