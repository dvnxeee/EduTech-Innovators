package com.daniel.springboot_crud.springboot_crud.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String nombreCurso;
    private String descripcion;
    private String instructor;
    private boolean cursoPublicado;
    private String reporteInscripcion;
    public Curso() {
    }
    public Curso(long id, String nombreCurso, String descripcion, String instructor, boolean cursoPublicado,
            String reporteInscripcion) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.cursoPublicado = cursoPublicado;
        this.reporteInscripcion = reporteInscripcion;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombreCurso() {
        return nombreCurso;
    }
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public boolean isCursoPublicado() {
        return cursoPublicado;
    }
    public void setCursoPublicado(boolean cursoPublicado) {
        this.cursoPublicado = cursoPublicado;
    }
    public String getReporteInscripcion() {
        return reporteInscripcion;
    }
    public void setReporteInscripcion(String reporteInscripcion) {
        this.reporteInscripcion = reporteInscripcion;
    }

    


    
}
