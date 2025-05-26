package com.edutechinnovators.springboot.app.springboot_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

//Esta anotación indica que esta clase es la clase principal de configuración de Spring Boot
//Spring Boot usa esta clase para iniciar y configurar todo el contexto de la aplicación automáticamente
@SpringBootApplication
public class SpringbootWebApplication {

    //Método principal que se ejecuta al iniciar la aplicación
    //Es el punto de entrada de la aplicación Java
    public static void main(String[] args) {
        //Llamo a SpringApplication.run para arrancar el framework Spring Boot
        //Esto hace que se inicialicen todos los beans, servicios, controladores y configuraciones
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

    //Definición de un Bean que se registrará en el contexto de Spring
    //Un Bean es un componente gestionado por Spring que puede ser inyectado y reutilizado
    //En este caso, el bean es un filtro HTTP que permite que los formularios HTML puedan enviar peticiones
    //con métodos HTTP como PUT, DELETE o PATCH usando un campo oculto "_method" en el formulario
    //Esto es necesario porque los formularios HTML estándar solo soportan GET y POST por defecto
    //Así, al incluir este filtro, puedo manejar correctamente las solicitudes HTTP con esos métodos "ocultos"
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        //Retorno una instancia del filtro HiddenHttpMethodFilter que Spring usará automáticamente
        return new HiddenHttpMethodFilter();
    }
}
