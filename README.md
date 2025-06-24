
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

📡 Endpoints REST por API
🔐 API - Administración (AdmiRestController)
Ruta base: /api/permisos-usuarios

GET / — Listar todos los permisos

GET /usuario/{usuarioId} — Listar permisos por usuario

GET /{id} — Obtener permiso por ID

POST / — Crear nuevo permiso

PUT /{id} — Actualizar permiso existente

DELETE /{id} — Eliminar permiso

📚 API - Cursos (CursoController)
Ruta base: /api/cursos

GET / — Listar cursos

GET /{id} — Obtener curso por ID

POST / — Crear curso

PUT /{id} — Actualizar curso

DELETE /{id} — Eliminar curso

📝 API - Evaluación y Seguimiento
EvaluacionController (/api/evaluaciones)

GET /

GET /{id}

POST /

PUT /{id}

DELETE /{id}

PreguntaController (/api/preguntas)

GET /

GET /{id}

POST /

PUT /{id}

DELETE /{id}

ProgresoEstudianteController (/api/progreso-estudiante)

GET /

GET /{id}

POST /

PUT /{id}

DELETE /{id}

RespuestaEstudianteController (/api/respuestas-estudiante)

GET /

GET /{id}

POST /

PUT /{id}

DELETE /{id}

🛠️ API - Soporte (TicketController)
Ruta base: /api/soporte

GET / — Listar tickets

GET /{id} — Obtener ticket por ID

POST / — Crear nuevo ticket

PUT /{id} — Editar ticket

DELETE /{id} — Eliminar ticket

👤 API - Usuarios (UsuarioRestController)
Ruta base: /api/usuarios

GET / — Listar usuarios

GET /activos — Listar usuarios activos

GET /{id} — Obtener usuario por ID

POST / — Crear usuario

PUT /{id} — Actualizar usuario

DELETE /{id} — Eliminar usuario

📄 API - Contenidos (ContenidoRestController)
Ruta base: /api/documentado/contenidos

GET / — Listar todos los contenidos

GET /{id} — Obtener contenido por ID

POST / — Crear nuevo contenido

PUT /{id} — Actualizar contenido

DELETE /{id} — Eliminar contenido


```bash
mvn test
```

## 👨‍💻 Autor

**Edutech Innovators**  
Proyecto de integración académica 2025

---

Este proyecto fue creado como trabajo práctico para aplicar conocimientos de Java, Spring Boot, y desarrollo web moderno.
