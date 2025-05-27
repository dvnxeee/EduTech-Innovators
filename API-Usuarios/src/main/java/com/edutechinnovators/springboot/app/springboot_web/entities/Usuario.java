package com.edutechinnovators.springboot.app.springboot_web.entities;

//Importaciones necesarias para indicar que esta clase representa una entidad en la base de datos.
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Anotación que indica que esta clase será mapeada como una tabla en la base de datos.
@Entity
//Indica explícitamente el nombre de la tabla que se creará o se usará en la base de datos.
@Table(name = "usuarios")
public class Usuario {

    //Identificador único para cada usuario. Será la clave primaria de la tabla.
    @Id
    //Se indica que el valor del ID será generado automáticamente por la base de datos (autoincremental).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Campos que representan atributos del usuario.
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenna;
    private String rol;

    //Campo booleano que indica si el usuario está activo o no (para desactivación lógica).
    private boolean activado;

    //Constructor vacío obligatorio para que JPA pueda instanciar objetos automáticamente.
    public Usuario() {
    }

    //Constructor con todos los parámetros, útil para pruebas o para crear usuarios desde código.
    public Usuario(Long id, String nombre, String apellido, String correo, String contrasenna, String rol, boolean activado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.rol = rol;
        this.activado = activado;
    }

    //Métodos getters y setters para permitir el acceso y modificación de los atributos privados.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    //Método adicional para mostrar el estado del usuario en formato texto.
    //Se usa en la vista (por ejemplo, en Thymeleaf) para mostrar “Activo” o “Inactivo”.
    public String getEstado() {
        return activado ? "Activo" : "Inactivo";
    }

}
