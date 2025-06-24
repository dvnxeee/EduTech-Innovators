package administracion.admi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import administracion.admi.entities.PermisosUsuarios;

public interface PermisosUsuariosRepository extends JpaRepository<PermisosUsuarios, Long> {

    // Método para encontrar permisos por usuarioId
    
    List<PermisosUsuarios> findByUsuarioId(Long usuarioId);

    // Método para encontrar permisos por tipoUsuario
    List<PermisosUsuarios> findByTipoUsuario(String tipoUsuario);

    // Método para encontrar permisos por permiso específico
    List<PermisosUsuarios> findByPermiso(String permiso);

}
