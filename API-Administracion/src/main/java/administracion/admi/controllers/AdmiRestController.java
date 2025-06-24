package administracion.admi.controllers;

import administracion.admi.entities.PermisosUsuarios;
import administracion.admi.services.PermisoUsuarioService;
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
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permisos-usuarios")
@Tag(name = "PermisosUsuarios", description = "Operaciones sobre permisos de usuarios")
public class AdmiRestController {

    @Autowired
    private PermisoUsuarioService service;

    private final String USUARIOS_API_URL = "http://localhost:8080/api/usuarios"; // Cambia el puerto si es necesario

    @Autowired
    private RestTemplate restTemplate;

    @Operation(summary = "Listar todos los permisos de usuarios")
    @GetMapping
    public List<PermisosUsuarios> listar() {
        return service.findAll();
    }

    @Operation(summary = "Listar permisos por usuarioId")
    @GetMapping("/usuario/{usuarioId}")
    public List<PermisosUsuarios> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.findByUsuarioId(usuarioId);
    }

    @Operation(summary = "Obtener un permiso por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Permiso encontrado"),
        @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<PermisosUsuarios> permisoOpt = service.findById(id);
        if (permisoOpt.isPresent()) {
            return ResponseEntity.ok(permisoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo permiso")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Permiso creado"),
        @ApiResponse(responseCode = "400", description = "Usuario no existe o datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PermisosUsuarios permiso) {
        // Validar que el usuario existe en la API de usuarios
        String url = USUARIOS_API_URL + "/" + permiso.getUsuarioId();
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                PermisosUsuarios creado = service.save(permiso);
                return ResponseEntity.status(HttpStatus.CREATED).body(creado);
            }
        } catch (Exception e) {
            // Usuario no encontrado o error de conexión
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El usuario con ID " + permiso.getUsuarioId() + " no existe en el sistema de usuarios.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("No se pudo validar el usuario.");
    }

    @Operation(summary = "Actualizar un permiso existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Permiso actualizado"),
        @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody PermisosUsuarios nuevosDatos) {
        PermisosUsuarios actualizado = service.update(id, nuevosDatos);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un permiso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Permiso eliminado"),
        @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<PermisosUsuarios> permisoOpt = service.findById(id);
        if (permisoOpt.isPresent()) {
            service.delete(permisoOpt.get());
            return ResponseEntity.ok(permisoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
}
