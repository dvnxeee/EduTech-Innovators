package administracion.admi.services;

import java.util.List;
import java.util.Optional;

import administracion.admi.entities.PermisosUsuarios;

   
public interface  PermisoUsuarioService {

    List<PermisosUsuarios> findAll();

    List<PermisosUsuarios> findByUsuarioId(Long usuarioId);

    Optional<PermisosUsuarios> findById(Long id);

    PermisosUsuarios save(PermisosUsuarios permisoUsuario);

    Optional<PermisosUsuarios> delete(PermisosUsuarios permisoUsuario);

    PermisosUsuarios update(Long id, PermisosUsuarios nuevosDatos);
    
    

}
