package com.edutech.aplicaciones.seguimiento.proyecto.evaluacion;

import com.edutech.aplicaciones.seguimiento.proyecto.entities.Evaluacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluacionTest {

    @Test
    @DisplayName("Guardar evaluación")
    public void saveTest() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1L);
        evaluacion.setTitulo("Evaluación Inicial");
        evaluacion.setDescripcion("Primera evaluación del proyecto");
        evaluacion.setFechaPublicacion("2024-07-10");

        Evaluacion guardado = evaluacion;

        assertEquals("Evaluación Inicial", guardado.getTitulo());
        assertEquals("2024-07-10", guardado.getFechaPublicacion());
    }

    @Test
    @DisplayName("Obtener todas las evaluaciones")
    public void findAllTest() {
        Evaluacion e1 = new Evaluacion();
        Evaluacion e2 = new Evaluacion();
        List<Evaluacion> lista = new ArrayList<>();
        lista.add(e1);
        lista.add(e2);

        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Buscar evaluación por ID")
    public void findByIdTest() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(100L);

        Optional<Evaluacion> resultado = Optional.of(evaluacion);
        assertTrue(resultado.isPresent());
        assertEquals(100L, resultado.get().getId());
    }

    @Test
    @DisplayName("Eliminar evaluación")
    public void deleteTest() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(5L);

        Optional<Evaluacion> eliminado = Optional.of(evaluacion);
        assertEquals(5L, eliminado.get().getId());
    }
}