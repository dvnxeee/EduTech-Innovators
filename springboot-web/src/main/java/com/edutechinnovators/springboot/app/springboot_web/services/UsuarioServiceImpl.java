package com.edutechinnovators.springboot.app.springboot_web.services;

import com.edutechinnovators.springboot.app.springboot_web.entities.Usuario;
import com.edutechinnovators.springboot.app.springboot_web.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //Anoto esta clase como un servicio para que Spring la detecte y la gestione automáticamente.
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired //Inyecto automáticamente el repositorio de usuarios sin necesidad de instanciarlo manualmente.
    private UsuarioRepository usuarioRepo;

    @Override
    @Transactional(readOnly = true) //Indico que este método solo realiza lectura (sin modificar datos), lo cual mejora el rendimiento.
    public List<Usuario> findAll() {
        //Devuelvo todos los usuarios existentes en la base de datos. 
        return (List<Usuario>) usuarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllActive() {
        //Uso un método personalizado del repositorio que solo devuelve usuarios activos (activado = true).
        return usuarioRepo.findByActivadoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        //Busco un usuario por su ID. Devuelvo un Optional para manejar el caso de que no se encuentre el usuario.
        return usuarioRepo.findById(id);
    }

    @Override
    @Transactional //Este método puede modificar la base de datos, por eso no es de solo lectura.
    public Usuario save(Usuario usuario) {
        //Si el usuario es nuevo (aún no tiene ID), lo marco como activado por defecto.
        if (usuario.getId() == null) {
            usuario.setActivado(true);
        }
        //Guardo el usuario (nuevo o editado) y retorno el objeto persistido.
        return usuarioRepo.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> delete(Usuario usuario) {
        //Intento buscar el usuario por su ID antes de eliminarlo.
        Optional<Usuario> usuarioOpcional = usuarioRepo.findById(usuario.getId());

        //Si el usuario existe, lo elimino de la base de datos.
        usuarioOpcional.ifPresent(usuarioDb -> {
            usuarioRepo.delete(usuario);
        });

        //Retorno el usuario eliminado o vacío si no se encontró.
        return usuarioOpcional;
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario nuevosDatos) {
        //Busco al usuario por ID, y si lo encuentro, actualizo sus datos con los valores nuevos.
        return usuarioRepo.findById(id).map(usuario -> {
            usuario.setNombre(nuevosDatos.getNombre());
            usuario.setApellido(nuevosDatos.getApellido());
            usuario.setCorreo(nuevosDatos.getCorreo());
            usuario.setContrasenna(nuevosDatos.getContrasenna());
            usuario.setRol(nuevosDatos.getRol());
            usuario.setActivado(nuevosDatos.isActivado());
            //Guardo los cambios y retorno el usuario actualizado.
            return usuarioRepo.save(usuario);
        }).orElse(null); //Si no se encuentra el usuario, retorno null.
    }

    @Override
    @Transactional
    public Usuario desactivate(Long id) {
        //Busco el usuario por ID, y si lo encuentro, cambio su estado a "desactivado".
        return usuarioRepo.findById(id).map(usuario -> {
            usuario.setActivado(false);
            return usuarioRepo.save(usuario); //Persisto el cambio.
        }).orElse(null); //Retorno null si no se encuentra el usuario.
    }
}
