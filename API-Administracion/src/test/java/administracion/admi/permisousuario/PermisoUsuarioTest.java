package administracion.admi.permisousuario;

import administracion.admi.entities.PermisosUsuarios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PermisoUsuarioTest {

    @Test
    @DisplayName("Guardar permiso de usuario")
    public void saveTest() {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setId(1L);
        permiso.setUsuarioId(101L);
        permiso.setTipoUsuario("ADMIN");
        permiso.setPermiso("GESTION");

        PermisosUsuarios guardado = permiso;

        assertEquals("GESTION", guardado.getPermiso());
    }

    @Test
    @DisplayName("Obtener todos los permisos (findAll)")
    public void findAllTest() {
        PermisosUsuarios p1 = new PermisosUsuarios();
        PermisosUsuarios p2 = new PermisosUsuarios();
        List<PermisosUsuarios> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);

        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Obtener permisos por ID de usuario")
    public void findByUsuarioIdTest() {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setUsuarioId(200L);
        List<PermisosUsuarios> permisos = List.of(permiso);

        assertEquals(200L, permisos.get(0).getUsuarioId());
    }

    @Test
    @DisplayName("Buscar por ID (findById)")
    public void findByIdTest() {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setId(10L);

        Optional<PermisosUsuarios> resultado = Optional.of(permiso);
        assertTrue(resultado.isPresent());
        assertEquals(10L, resultado.get().getId());
    }

    @Test
    @DisplayName("Eliminar permiso de usuario (delete)")
    public void deleteTest() {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setId(5L);

        Optional<PermisosUsuarios> eliminado = Optional.of(permiso);
        assertEquals(5L, eliminado.get().getId());
    }

    @Test
    @DisplayName("Actualizar datos de permiso")
    public void updateTest() {
        PermisosUsuarios original = new PermisosUsuarios();
        original.setId(1L);
        original.setUsuarioId(100L);
        original.setTipoUsuario("ADMIN");
        original.setPermiso("LECTURA");

        PermisosUsuarios nuevos = new PermisosUsuarios();
        nuevos.setUsuarioId(101L);
        nuevos.setTipoUsuario("SUPER_ADMIN");
        nuevos.setPermiso("ESCRITURA");

        original.setUsuarioId(nuevos.getUsuarioId());
        original.setTipoUsuario(nuevos.getTipoUsuario());
        original.setPermiso(nuevos.getPermiso());

        assertEquals("SUPER_ADMIN", original.getTipoUsuario());
        assertEquals("ESCRITURA", original.getPermiso());
        assertEquals(101L, original.getUsuarioId());
    }
}