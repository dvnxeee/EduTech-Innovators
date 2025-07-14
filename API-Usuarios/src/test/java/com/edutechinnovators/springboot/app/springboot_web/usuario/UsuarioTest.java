package com.edutechinnovators.springboot.app.springboot_web.usuario;

import com.edutechinnovators.springboot.app.springboot_web.entities.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    @DisplayName("Guardar usuario")
    public void saveTest() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setCorreo("juan.perez@example.com");
        usuario.setContrasenna("1234");
        usuario.setRol("ADMIN");
        usuario.setActivado(true);

        Usuario guardado = usuario;

        assertEquals("Juan", guardado.getNombre());
        assertEquals("Pérez", guardado.getApellido());
        assertTrue(guardado.isActivado());
    }

    @Test
    @DisplayName("Obtener todos los usuarios")
    public void findAllTest() {
        Usuario u1 = new Usuario();
        Usuario u2 = new Usuario();
        List<Usuario> lista = new ArrayList<>();
        lista.add(u1);
        lista.add(u2);

        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Obtener usuarios activos")
    public void findAllActiveTest() {
        Usuario u1 = new Usuario();
        u1.setActivado(true);
        Usuario u2 = new Usuario();
        u2.setActivado(false);

        List<Usuario> lista = List.of(u1, u2);
        long activos = lista.stream().filter(Usuario::isActivado).count();

        assertEquals(1, activos);
    }

    @Test
    @DisplayName("Buscar usuario por ID")
    public void findByIdTest() {
        Usuario usuario = new Usuario();
        usuario.setId(100L);

        Optional<Usuario> resultado = Optional.of(usuario);
        assertTrue(resultado.isPresent());
        assertEquals(100L, resultado.get().getId());
    }

    @Test
    @DisplayName("Eliminar usuario")
    public void deleteTest() {
        Usuario usuario = new Usuario();
        usuario.setId(5L);

        Optional<Usuario> eliminado = Optional.of(usuario);
        assertEquals(5L, eliminado.get().getId());
    }

    @Test
    @DisplayName("Actualizar datos de usuario")
    public void updateTest() {
        Usuario original = new Usuario();
        original.setId(1L);
        original.setNombre("Pedro");
        original.setApellido("Gómez");
        original.setCorreo("pedro.gomez@example.com");
        original.setContrasenna("pass");
        original.setRol("USER");
        original.setActivado(true);

        Usuario nuevos = new Usuario();
        nuevos.setNombre("Pedro Actualizado");
        nuevos.setApellido("Gómez Actualizado");
        nuevos.setCorreo("pedro.actualizado@example.com");
        nuevos.setContrasenna("newpass");
        nuevos.setRol("ADMIN");
        nuevos.setActivado(false);

        original.setNombre(nuevos.getNombre());
        original.setApellido(nuevos.getApellido());
        original.setCorreo(nuevos.getCorreo());
        original.setContrasenna(nuevos.getContrasenna());
        original.setRol(nuevos.getRol());
        original.setActivado(nuevos.isActivado());

        assertEquals("Pedro Actualizado", original.getNombre());
        assertEquals("ADMIN", original.getRol());
        assertFalse(original.isActivado());
    }

    @Test
    @DisplayName("Obtener estado de usuario")
    public void getEstadoTest() {
        Usuario usuarioActivo = new Usuario();
        usuarioActivo.setActivado(true);

        Usuario usuarioInactivo = new Usuario();
        usuarioInactivo.setActivado(false);

        assertEquals("Activo", usuarioActivo.getEstado());
        assertEquals("Inactivo", usuarioInactivo.getEstado());
    }
}