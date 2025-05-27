
# Proyecto Integrado - Plataforma Educativa

Este proyecto es una aplicación web desarrollada con **Spring Boot**, como parte del trabajo de integración de contenidos educativos. Su objetivo es ofrecer una plataforma de gestión de usuarios, cursos y soporte, orientada a instituciones o academias.

## ✨ Características principales

- Gestión de usuarios (crear, editar, listar, eliminar)
- Módulo de soporte técnico (tickets)
- Gestión de cursos y vistas asociadas
- API REST para operaciones sobre entidades
- Vistas con Thymeleaf y Bootstrap

## 🧰 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Maven
- Spring Data JPA
- Thymeleaf
- Bootstrap (en vistas)
- H2 / MySQL
- Git & GitHub

## 📁 Estructura del proyecto

```
Proyecto Integrado/
├── src/
│   └── main/
│       ├── java/com/edutech/Aplicaciones/
│       │   ├── controllers/         # Controladores web y REST
│       │   ├── entities/            # Clases de dominio (JPA)
│       │   ├── repository/          # Repositorios JPA
│       │   ├── services/            # Lógica de negocio
│       │   └── EdutechApplication.java
│       └── resources/
│           ├── static/              # Recursos estáticos (CSS, JS)
│           ├── templates/           # Vistas Thymeleaf
│           └── application.properties
├── pom.xml                          # Dependencias y configuración de Maven
├── mvnw / mvnw.cmd                  # Maven Wrapper
└── README.md
```

## ⚙️ Configuración rápida

1. **Clona el repositorio:**
```bash
git clone https://github.com/usuario/proyecto-integrado.git
cd proyecto-integrado
```

2. **Ejecuta con Maven:**
```bash
mvn spring-boot:run
```

3. **Accede desde tu navegador:**
```
http://localhost:8080
```

## 📄 Archivo de configuración

En `src/main/resources/application.properties`, puedes cambiar los valores de conexión a la base de datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/edutech_db
spring.datasource.username=root
spring.datasource.password=tu_clave
spring.jpa.hibernate.ddl-auto=update
```

## 🧪 Pruebas

Para ejecutar los tests:

Usuarios
Vista: usuarios/lista
Crud: api/usuarios

Cursos
Vista: vista/cursos
Crud: api/cursos

Soporte
Vista: tickets/nuevo
Crud: api/soporte

https://1024-186-11-49-210.ngrok-free.app

```bash
mvn test
```

## 👨‍💻 Autor

**Edutech Innovators**  
Proyecto de integración académica 2025

---

Este proyecto fue creado como trabajo práctico para aplicar conocimientos de Java, Spring Boot, y desarrollo web moderno.
