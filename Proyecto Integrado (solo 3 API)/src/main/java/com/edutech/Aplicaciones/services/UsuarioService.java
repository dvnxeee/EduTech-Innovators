package com.edutech.Aplicaciones.services;

import java.util.List;
import java.util.Optional;

import com.edutech.Aplicaciones.entities.Usuario;

//Esta es la interfaz del servicio de Usuario. Aquí defino los métodos que 
//el servicio debe implementar para manejar la lógica de negocio relacionada con los usuarios.
public interface UsuarioService {

    //Este método devuelve una lista con todos los usuarios registrados en el sistema.
    List<Usuario> findAll();

    //Este método devuelve únicamente los usuarios que están activados (activado = true).
    List<Usuario> findAllActive();

    //Este método busca un usuario por su ID.
    //Retorna un Optional para manejar de forma segura si el usuario existe o no.
    Optional<Usuario> findById(Long id);

    //Este método guarda un nuevo usuario o actualiza uno existente.
    //Se utiliza tanto para crear como para editar usuarios.
    Usuario save(Usuario usuario);

    //Este método intenta eliminar un usuario del sistema.
    //Si lo encuentra, lo elimina y devuelve el usuario eliminado dentro de un Optional.
    Optional<Usuario> delete(Usuario usuario);

    //Este método actualiza un usuario específico, identificado por su ID.
    //Se usa principalmente desde APIs REST.
    Usuario update(Long id, Usuario usuario);

    //Este método desactiva un usuario, cambiando su campo `activado` a false.
    Usuario desactivate(Long id);
}
