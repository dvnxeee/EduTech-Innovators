package com.edutechinnovators.springboot.app.springboot_web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edutechinnovators.springboot.app.springboot_web.entities.Usuario;

//Esta interfaz representa el componente de acceso a datos (DAO) para la entidad Usuario.
//Al extender CrudRepository, Spring Data JPA genera automáticamente los métodos CRUD básicos.
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    //Este método personalizado permite buscar todos los usuarios que tengan un rol específico.
    //Spring crea automáticamente la consulta basada en el nombre del método (por convención).
    List<Usuario> findByRol(String rol);

    //Este método devuelve únicamente los usuarios que están activados (activado = true).
    //Es útil cuando queremos listar solo los usuarios en estado activo.
    List<Usuario> findByActivadoTrue();
}
