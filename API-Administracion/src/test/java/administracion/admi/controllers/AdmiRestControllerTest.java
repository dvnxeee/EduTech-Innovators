package administracion.admi.controllers;

import administracion.admi.entities.PermisosUsuarios;
import administracion.admi.services.PermisoUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdmiRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PermisoUsuarioService permisoUsuarioService;

    @MockitoBean
    private RestTemplate restTemplate;

    @Test
    public void listarPermisosTest() throws Exception {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setId(1L);
        permiso.setUsuarioId(10L);
        permiso.setTipoUsuario("ADMIN");
        permiso.setPermiso("GESTIONAR_USUARIOS");

        when(permisoUsuarioService.findAll()).thenReturn(Arrays.asList(permiso));

        mockMvc.perform(get("/api/permisos-usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void listarPermisosPorUsuarioTest() throws Exception {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setId(2L);
        permiso.setUsuarioId(20L);
        permiso.setTipoUsuario("USER");
        permiso.setPermiso("VER_CURSOS");

        when(permisoUsuarioService.findByUsuarioId(20L)).thenReturn(Arrays.asList(permiso));

        mockMvc.perform(get("/api/permisos-usuarios/usuario/20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verDetallePermisoTest() {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setId(3L);
        permiso.setUsuarioId(30L);
        permiso.setTipoUsuario("PROFESOR");
        permiso.setPermiso("EDITAR_PERFIL");
        try {
            when(permisoUsuarioService.findById(3L)).thenReturn(Optional.of(permiso));
            mockMvc.perform(get("/api/permisos-usuarios/3")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing lanzó un error " + ex.getMessage());
        }
    }

    @Test
    public void permisoNoExisteTest() throws Exception {
        when(permisoUsuarioService.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/permisos-usuarios/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearPermisoTest() throws Exception {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setUsuarioId(40L);
        permiso.setTipoUsuario("ESTUDIANTE");
        permiso.setPermiso("VER_CURSOS");

        PermisosUsuarios saved = new PermisosUsuarios();
        saved.setId(5L);
        saved.setUsuarioId(40L);
        saved.setTipoUsuario("ESTUDIANTE");
        saved.setPermiso("VER_CURSOS");

        when(permisoUsuarioService.save(any(PermisosUsuarios.class))).thenReturn(saved);
        // Mock necesario para que la validación del usuario externo no falle
        when(restTemplate.getForEntity(any(String.class), eq(Object.class)))
                .thenReturn(org.springframework.http.ResponseEntity.ok().build());

        mockMvc.perform(post("/api/permisos-usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(permiso)))
                .andExpect(status().isCreated());
    }

    @Test
    public void eliminarPermisoTest() throws Exception {
        PermisosUsuarios permiso = new PermisosUsuarios();
        permiso.setId(6L);
        permiso.setUsuarioId(50L);
        permiso.setTipoUsuario("SOPORTE");
        permiso.setPermiso("ELIMINAR_USUARIO");

        when(permisoUsuarioService.findById(6L)).thenReturn(Optional.of(permiso));

        mockMvc.perform(delete("/api/permisos-usuarios/6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarPermisoNoExisteTest() throws Exception {
        when(permisoUsuarioService.findById(100L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/permisos-usuarios/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
