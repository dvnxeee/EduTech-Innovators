package com.edutechinnovators.springboot.app.springboot_web.controllers;

import com.edutechinnovators.springboot.app.springboot_web.entities.Usuario;
import com.edutechinnovators.springboot.app.springboot_web.services.UsuarioService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//Esta clase es un controlador MVC de Spring para manejar las vistas relacionadas con usuarios.
//Utiliza Thymeleaf para devolver vistas HTML en vez de respuestas JSON.
@Controller
//Ruta base para todas las solicitudes de este controlador: /usuarios
@RequestMapping("/usuarios")
public class UsuarioViewController {

    //Inyección del servicio que maneja la lógica de negocio para usuarios.
    @Autowired
    private UsuarioService usuarioService;

    //Mapeo GET para mostrar la lista de usuarios.
    //Añade al modelo la lista completa de usuarios para ser mostrada en la vista "usuarios/lista".
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/lista"; //Nombre de la plantilla Thymeleaf que se renderizará (templates/usuarios/lista.html)
    }

    //Mapeo GET para mostrar el formulario para crear un nuevo usuario.
    //Añade un objeto Usuario vacío al modelo para enlazar con el formulario.
    @GetMapping("/form")
    public String formularioCrear(Model model) {
        model.addAttribute("usuario", new Usuario()); //Usuario vacío para el formulario
        return "usuarios/formulario"; //Vista para el formulario
    }

    //Mapeo GET para mostrar el formulario para editar un usuario existente.
    //Recibe el ID como variable en la URL, busca el usuario y lo añade al modelo para rellenar el formulario.
    @GetMapping("/form/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);
        if (usuarioOpt.isPresent()) {
            model.addAttribute("usuario", usuarioOpt.get());
            return "usuarios/formulario"; // Vista del formulario con datos cargados para edición
        }
        // Si no se encuentra el usuario con ese ID, redirige a la lista de usuarios
        return "redirect:/usuarios/lista";
    }

    // Mapeo POST para crear un usuario nuevo.
    // Recibe los datos del formulario enlazados en un objeto Usuario.
    // Convierte el rol a mayúsculas para mantener consistencia y guarda el usuario.
    // Luego redirige a la lista de usuarios.
    @PostMapping("/crear")
    public String crearUsuario(Usuario usuario) {
        usuario.setRol(usuario.getRol().toUpperCase()); // Normalizar rol en mayúsculas
        usuarioService.save(usuario);
        return "redirect:/usuarios/lista";
    }

    // Mapeo POST para editar un usuario existente.
    // Recibe el ID del usuario a modificar y los datos del formulario.
    // Si el usuario existe, mantiene la contraseña anterior si el campo está vacío.
    // También convierte el rol a mayúsculas y asegura que el ID se mantiene antes de guardar.
    // Finalmente redirige a la lista de usuarios.
    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Usuario usuarioForm) {
        Optional<Usuario> usuarioExistenteOpt = usuarioService.findById(id);
        
        if (usuarioExistenteOpt.isPresent()) {
            Usuario usuarioExistente = usuarioExistenteOpt.get();

            // Verificar si se dejó vacío el campo contraseña en el formulario
            // Si es así, mantiene la contraseña actual para no sobrescribirla con vacío.
            if (usuarioForm.getContrasenna() == null || usuarioForm.getContrasenna().isEmpty()) {
                usuarioForm.setContrasenna(usuarioExistente.getContrasenna());
            }

            // Convertir el rol a mayúsculas para mantener consistencia en la base de datos.
            usuarioForm.setRol(usuarioForm.getRol().toUpperCase());

            // Asignar el ID del usuario existente para que se actualice en vez de crear uno nuevo.
            usuarioForm.setId(id);

            // Guardar los cambios realizados
            usuarioService.save(usuarioForm);
        }

        // Redirigir a la lista de usuarios tras la edición.
        return "redirect:/usuarios/lista";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuarioService.delete(usuario);
        return "redirect:/usuarios/lista";
    }

}
