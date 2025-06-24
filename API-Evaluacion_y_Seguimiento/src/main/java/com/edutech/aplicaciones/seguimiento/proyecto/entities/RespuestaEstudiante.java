package com.edutech.aplicaciones.seguimiento.proyecto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "respuesta_estudiante")
public class RespuestaEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long estudianteId;
    private long preguntaId;
    private String respuesta;
    private boolean esCorrecta;
    private String fechaRespuesta;

    public RespuestaEstudiante() {
    }

    public RespuestaEstudiante(long id, long estudianteId, long preguntaId, String respuesta, boolean esCorrecta,
            String fechaRespuesta) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.preguntaId = preguntaId;
        this.respuesta = respuesta;
        this.esCorrecta = esCorrecta;
        this.fechaRespuesta = fechaRespuesta;
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

    public long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

}