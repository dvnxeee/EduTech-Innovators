package com.edutech.contenido.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="contenido")
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContenido;

    private String idCurso;
    private String nombreContenido;
    private String tipoContenido;
    private String url;
    private Boolean subido;
    private Boolean accesoAlumno;
    private String fechaUltimaSubidaContenido;
    private String etiquetas;
    
    public Contenido() {
    }

    public Contenido(Long idContenido, String idCurso, String nombreContenido, String tipoContenido, String url,
            Boolean subido, Boolean accesoAlumno, String fechaUltimaSubidaContenido, String etiquetas) {
        this.idContenido = idContenido;
        this.idCurso = idCurso;
        this.nombreContenido = nombreContenido;
        this.tipoContenido = tipoContenido;
        this.url = url;
        this.subido = subido;
        this.accesoAlumno = accesoAlumno;
        this.fechaUltimaSubidaContenido = fechaUltimaSubidaContenido;
        this.etiquetas = etiquetas;
    }

    public Long getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Long idContenido) {
        this.idContenido = idContenido;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreContenido() {
        return nombreContenido;
    }

    public void setNombreContenido(String nombreContenido) {
        this.nombreContenido = nombreContenido;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSubido() {
        return subido;
    }

    public void setSubido(Boolean subido) {
        this.subido = subido;
    }

    public Boolean getAccesoAlumno() {
        return accesoAlumno;
    }

    public void setAccesoAlumno(Boolean accesoAlumno) {
        this.accesoAlumno = accesoAlumno;
    }

    public String getFechaUltimaSubidaContenido() {
        return fechaUltimaSubidaContenido;
    }

    public void setFechaUltimaSubidaContenido(String fechaUltimaSubidaContenido) {
        this.fechaUltimaSubidaContenido = fechaUltimaSubidaContenido;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    

}
