package com.edutechinnovators.springboot.app.springboot_web.controllers;

import com.edutechinnovators.springboot.app.springboot_web.entities.Usuario;
import com.edutechinnovators.springboot.app.springboot_web.services.UsuarioService;

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

//Esta clase es un controlador REST de Spring que maneja las peticiones HTTP relacionadas con usuarios.
//La anotación @RestController indica que los métodos retornan directamente respuestas JSON o similares,
//sin necesidad de usar vistas (templates).
@RestController
//Define la ruta base para todas las solicitudes de este controlador: /api/usuarios
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    //Inyección automática del servicio que contiene la lógica para gestionar usuarios.
    @Autowired
    private UsuarioService service;

    //Método para listar todos los usuarios.
    //Mapeado a GET /api/usuarios
    //Retorna una lista con todos los usuarios en formato JSON.
    @GetMapping
    public List<Usuario> listar() {
        return service.findAll();
    }

    //Método para listar solo los usuarios activos.
    //Mapeado a GET /api/usuarios/activos
    //Retorna una lista con usuarios que están activos.
    @GetMapping("/activos")
    public List<Usuario> listarActivos() {
        return service.findAllActive();
    }

    //Método para obtener un usuario específico por su ID.
    //Mapeado a GET /api/usuarios/{id}
    //Recibe el ID como variable en la URL.
    //Retorna un ResponseEntity que puede contener el usuario encontrado o un estado 404 si no existe.
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.findById(id);
        if (usuarioOptional.isPresent()) {
            //Usuario encontrado, retorno con status 200 OK y el usuario en el cuerpo.
            return ResponseEntity.ok(usuarioOptional.get());
        }
        //Usuario no encontrado, retorno un 404 Not Found sin cuerpo.
        return ResponseEntity.notFound().build();
    }

    //Método para crear un nuevo usuario.
    //Mapeado a POST /api/usuarios
    //Recibe un objeto Usuario en el cuerpo de la petición (JSON).
    //Retorna el usuario creado con código 201 CREATED.
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario unUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }

    //Método para actualizar un usuario existente.
    //Mapeado a PUT /api/usuarios/{id}
    //Recibe el ID en la URL y el usuario con datos actualizados en el cuerpo (JSON).
    //Si el usuario existe, actualiza los campos y retorna el usuario actualizado con status 200 OK.
    //Si no existe, retorna 404 Not Found.
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario unUsuario) {
        Optional<Usuario> usuarioOptional = service.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario existente = usuarioOptional.get();
            //Actualizo los campos básicos
            existente.setNombre(unUsuario.getNombre());
            existente.setApellido(unUsuario.getApellido());
            existente.setCorreo(unUsuario.getCorreo());

            //La contraseña se actualiza solo si el nuevo valor no es nulo ni vacío
            if (unUsuario.getContrasenna() != null && !unUsuario.getContrasenna().isEmpty()) {
                existente.setContrasenna(unUsuario.getContrasenna());
            }

            existente.setRol(unUsuario.getRol());
            existente.setActivado(unUsuario.isActivado());

            //Guardo los cambios y retorno el usuario actualizado
            Usuario actualizado = service.save(existente);
            return ResponseEntity.ok(actualizado);
        }
        //Si el usuario no existe, retorno 404 Not Found
        return ResponseEntity.notFound().build();
    }

    //Método para eliminar un usuario.
    //Mapeado a DELETE /api/usuarios/{id}
    //Recibe el ID del usuario a eliminar en la URL.
    //Elimina el usuario y retorna el usuario eliminado si existe,
    //o 404 Not Found si no existe.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        //Creo un usuario temporal solo con el ID para pasarlo al servicio
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);

        //El servicio intenta eliminar y devuelve un Optional con el usuario eliminado o vacío si no existe
        Optional<Usuario> eliminado = service.delete(unUsuario);
        if (eliminado.isPresent()) {
            return ResponseEntity.ok(eliminado.get());
        }
        return ResponseEntity.notFound().build();
    }

}
