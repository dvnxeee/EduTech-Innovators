package com.edutech.aplicaciones.seguimiento.proyecto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "progreso_estudiante")
public class ProgresoEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long estudianteId;
    private long cursoId;
    private double porcentajeCompletado;
    private double calificacionPromedio;

    public ProgresoEstudiante() {
    }

    public ProgresoEstudiante(long id, long estudianteId, long cursoId, double porcentajeCompletado,
            double calificacionPromedio) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.porcentajeCompletado = porcentajeCompletado;
        this.calificacionPromedio = calificacionPromedio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public long getCursoId() {
        return cursoId;
    }

    public void setCursoId(long cursoId) {
        this.cursoId = cursoId;
    }

    public double getPorcentajeCompletado() {
        return porcentajeCompletado;
    }

    public void setPorcentajeCompletado(double porcentajeCompletado) {
        this.porcentajeCompletado = porcentajeCompletado;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }
    
}