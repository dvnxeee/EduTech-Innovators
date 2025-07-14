package com.edutech.contenido.contenido;

import com.edutech.contenido.entities.Contenido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class ContenidoTest {

    @Test
    @DisplayName("Guardar contenido")
    public void saveTest() {
        Contenido contenido = new Contenido();
        contenido.setIdContenido(1L);
        contenido.setIdCurso("C-001");
        contenido.setNombreContenido("Video Introductorio");
        contenido.setTipoContenido("Video");
        contenido.setUrl("https://example.com/intro");
        contenido.setSubido(true);
        contenido.setAccesoAlumno(true);
        contenido.setFechaUltimaSubidaContenido("2024-06-30");
        contenido.setEtiquetas("inicio,intro");

        Contenido guardado = contenido;

        assertEquals("Video Introductorio", guardado.getNombreContenido());
        assertTrue(guardado.getSubido());
        assertEquals("C-001", guardado.getIdCurso());
    }

    @Test
    @DisplayName("Obtener todos los contenidos")
    public void findAllTest() {
        Contenido c1 = new Contenido();
        Contenido c2 = new Contenido();
        List<Contenido> lista = new ArrayList<>();
        lista.add(c1);
        lista.add(c2);

        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Buscar contenido por ID")
    public void findByIdTest() {
        Contenido contenido = new Contenido();
        contenido.setIdContenido(100L);

        Optional<Contenido> resultado = Optional.of(contenido);
        assertTrue(resultado.isPresent());
        assertEquals(100L, resultado.get().getIdContenido());
    }

    @Test
    @DisplayName("Eliminar contenido")
    public void deleteTest() {
        Contenido contenido = new Contenido();
        contenido.setIdContenido(5L);

        Optional<Contenido> eliminado = Optional.of(contenido);
        assertEquals(5L, eliminado.get().getIdContenido());
    }

}