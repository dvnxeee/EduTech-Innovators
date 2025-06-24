package com.edutech.contenido.services;

import com.edutech.contenido.entities.Contenido;
import com.edutech.contenido.repository.ContenidoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;//

import org.mockito.InjectMocks;//
import org.mockito.Mock;//
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;//
import static org.mockito.Mockito.*;

public class ContenidoServiceImplTest {

    @InjectMocks
    private ContenidoServiceImpl service;

    @Mock
    private ContenidoRepository repository;

    List<Contenido> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.chargeContenido();
    }

    @Test
    @DisplayName("Test para listar todos los contenidos")
    public void findByAllTest() {
        when(repository.findAll()).thenReturn(list);

        List<Contenido> response = service.findByAll();

        assertEquals(2, response.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test para buscar un contenido por ID")
    public void findByIdTest() {
        Contenido contenido = list.get(0);
        when(repository.findById(1L)).thenReturn(Optional.of(contenido));

        Optional<Contenido> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Java Básico", result.get().getNombreContenido());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test para guardar un contenido")
    public void saveTest() {
        Contenido nuevo = new Contenido(3L, "CURSO05", "Nuevo Contenido", "VIDEO", "url", true, false, "2025-06-18", "nuevo");

        when(repository.save(nuevo)).thenReturn(nuevo);

        Contenido resultado = service.save(nuevo);

        assertNotNull(resultado);
        assertEquals("Nuevo Contenido", resultado.getNombreContenido());
        verify(repository, times(1)).save(nuevo);
    }

    @Test
    @DisplayName("Test para eliminar un contenido existente")
    public void deleteTest() {
        Contenido contenido = list.get(0);
        when(repository.findById(1L)).thenReturn(Optional.of(contenido));

        Optional<Contenido> eliminado = service.delete(contenido);

        assertTrue(eliminado.isPresent());
        verify(repository, times(1)).delete(contenido);
    }

    @Test
    @DisplayName("Test para eliminar contenido no existente")
    public void deleteNotFoundTest() {
        Contenido noExiste = new Contenido();
        noExiste.setIdContenido(99L);

        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Contenido> resultado = service.delete(noExiste);

        assertFalse(resultado.isPresent());
        verify(repository, never()).delete(any());
    }

    // Método auxiliar para poblar lista
    public void chargeContenido() {
        Contenido contenido1 = new Contenido(
                1L,
                "CURSO03",
                "Java Básico",
                "PDF",
                "http://ejemplo.com/java.pdf",
                true,
                true,
                "2025-06-16",
                "java,introduccion"
        );

        Contenido contenido2 = new Contenido(
                2L,
                "CURSO04",
                "Spring Boot Video",
                "VIDEO",
                "https://youtube.com/video",
                false,
                true,
                "2025-06-17",
                "spring,backend"
        );

        list.clear();
        list.add(contenido1);
        list.add(contenido2);
    }
}

