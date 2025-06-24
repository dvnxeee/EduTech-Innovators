package administracion.admi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import administracion.admi.repository.PermisosUsuariosRepository;
import administracion.admi.entities.PermisosUsuarios;


@Service
public class PermisoUsuarioServiceImpl implements PermisoUsuarioService {

    @Autowired
    private PermisosUsuariosRepository permisoUsuarioRepo;

    @Override
    @Transactional(readOnly = true)
    public List<PermisosUsuarios> findAll() {
        return permisoUsuarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermisosUsuarios> findByUsuarioId(Long usuarioId) {
        return permisoUsuarioRepo.findByUsuarioId(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisosUsuarios> findById(Long id) {
        return permisoUsuarioRepo.findById(id);
    }

    @Override
    @Transactional
    public PermisosUsuarios save(PermisosUsuarios permisoUsuario) {
        return permisoUsuarioRepo.save(permisoUsuario);
    }

    @Override
    @Transactional
    public Optional<PermisosUsuarios> delete(PermisosUsuarios permisoUsuario) {
        Optional<PermisosUsuarios> permisoOpcional = permisoUsuarioRepo.findById(permisoUsuario.getId());
        permisoOpcional.ifPresent(permiso -> permisoUsuarioRepo.delete(permisoUsuario));
        return permisoOpcional;
    }

    @Override
    @Transactional
    public PermisosUsuarios update(Long id, PermisosUsuarios nuevosDatos) {
        return permisoUsuarioRepo.findById(id).map(permiso -> {
            permiso.setUsuarioId(nuevosDatos.getUsuarioId());
            permiso.setTipoUsuario(nuevosDatos.getTipoUsuario());
            permiso.setPermiso(nuevosDatos.getPermiso());
            return permisoUsuarioRepo.save(permiso);
        }).orElse(null);
    }
}
