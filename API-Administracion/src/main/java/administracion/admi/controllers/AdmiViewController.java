package administracion.admi.controllers;

import administracion.admi.entities.PermisosUsuarios;
import administracion.admi.services.PermisoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Controller
@RequestMapping("/permisos")
public class AdmiViewController {

    @Autowired
    private PermisoUsuarioService permisoUsuarioService;

    private final String USUARIOS_API_URL = "http://localhost:8080/api/usuarios"; // Cambia el puerto si es necesario

    private final RestTemplate restTemplate = new RestTemplate();

    // Lista de permisos
    @GetMapping("/lista")
    public String listarPermisos(Model model) {
        model.addAttribute("permisos", permisoUsuarioService.findAll());
        return "permisos/lista";
    }

    // Formulario para crear un nuevo permiso
    @GetMapping("/form")
    public String formularioCrear(Model model) {
        model.addAttribute("permiso", new PermisosUsuarios());
        return "permisos/formulario";
    }

    // Formulario para editar un permiso existente
    @GetMapping("/form/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Optional<PermisosUsuarios> permisoOpt = permisoUsuarioService.findById(id);
        if (permisoOpt.isPresent()) {
            model.addAttribute("permiso", permisoOpt.get());
            return "permisos/formulario";
        }
        return "redirect:/permisos/lista";
    }

    // Guardar nuevo permiso
    @PostMapping("/crear")
    public String crearPermiso(PermisosUsuarios permiso, Model model) {
        // Validar que el usuario existe en la API de usuarios
        String url = USUARIOS_API_URL + "/" + permiso.getUsuarioId();
        try {
            restTemplate.getForEntity(url, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            model.addAttribute("permiso", permiso);
            model.addAttribute("error", "El usuario con ID " + permiso.getUsuarioId() + " no existe.");
            model.addAttribute("tiposUsuario", getTiposUsuario());
            model.addAttribute("permisos", getPermisos());
            return "permisos/formulario";
        } catch (Exception e) {
            model.addAttribute("permiso", permiso);
            model.addAttribute("error", "Error al validar el usuario.");
            model.addAttribute("tiposUsuario", getTiposUsuario());
            model.addAttribute("permisos", getPermisos());
            return "permisos/formulario";
        }
        permisoUsuarioService.save(permiso);
        return "redirect:/permisos/lista";
    }

    // Editar permiso existente
    @PostMapping("/editar/{id}")
    public String editarPermiso(@PathVariable Long id, PermisosUsuarios permisoForm) {
        permisoForm.setId(id);
        permisoUsuarioService.save(permisoForm);
        return "redirect:/permisos/lista";
    }

    // Eliminar permiso
    @PostMapping("/eliminar/{id}")
    public String eliminarPermiso(@PathVariable Long id) {
        permisoUsuarioService.findById(id).ifPresent(permisoUsuarioService::delete);
        return "redirect:/permisos/lista";
    }

    // Formulario para asignar múltiples permisos
    @GetMapping("/form-multiple")
    public String formularioMultiple(Model model) {
        model.addAttribute("tiposUsuario", getTiposUsuario());
        model.addAttribute("permisos", getPermisos());
        return "permisos/formulario-multiple";
    }

    @PostMapping("/crear-multiple")
    public String crearPermisosMultiples(
            @RequestParam Long usuarioId,
            @RequestParam String tipoUsuario,
            @RequestParam(name = "permisosSeleccionados", required = false) String[] permisosSeleccionados,
            Model model) {

        // Validar usuario
        String url = USUARIOS_API_URL + "/" + usuarioId;
        try {
            restTemplate.getForEntity(url, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            model.addAttribute("error", "El usuario con ID " + usuarioId + " no existe.");
            model.addAttribute("tiposUsuario", getTiposUsuario());
            model.addAttribute("permisos", getPermisos());
            return "permisos/formulario-multiple";
        } catch (Exception e) {
            model.addAttribute("error", "Error al validar el usuario.");
            model.addAttribute("tiposUsuario", getTiposUsuario());
            model.addAttribute("permisos", getPermisos());
            return "permisos/formulario-multiple";
        }

        // Validar que haya al menos un permiso seleccionado
        if (permisosSeleccionados == null || permisosSeleccionados.length == 0) {
            model.addAttribute("error", "Debes seleccionar al menos un permiso.");
            model.addAttribute("tiposUsuario", getTiposUsuario());
            model.addAttribute("permisos", getPermisos());
            return "permisos/formulario-multiple";
        }

        // Guardar cada permiso seleccionado
        for (String permiso : permisosSeleccionados) {
            PermisosUsuarios nuevo = new PermisosUsuarios();
            nuevo.setUsuarioId(usuarioId);
            nuevo.setTipoUsuario(tipoUsuario);
            nuevo.setPermiso(permiso);
            permisoUsuarioService.save(nuevo);
        }
        return "redirect:/permisos/lista";
    }

    // Métodos para opciones fijas
    @ModelAttribute("tiposUsuario")
    public String[] getTiposUsuario() {
        return new String[]{"ADMIN", "USER", "PROFESOR", "SOPORTE", "ESTUDIANTE"};
    }

    @ModelAttribute("permisos")
    public String[] getPermisos() {
        return new String[]{"VER_CURSOS", "GESTIONAR_USUARIOS", "EDITAR_PERFIL", "ELIMINAR_USUARIO"};
    }
}
