package com.edutechinnovators.springboot.app.springboot_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootWebApplication {

    //Método principal que se ejecuta al iniciar la aplicación
    //Es el punto de entrada de la aplicación Java
    public static void main(String[] args) {
        //Llamo a SpringApplication.run para arrancar el framework Spring Boot
        //Esto hace que se inicialicen todos los beans, servicios, controladores y configuraciones
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

    
}
