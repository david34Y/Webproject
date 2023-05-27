package com.example.webproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  idpublicacion;
    private String titulo;
    private String resumen;

    private String texto;

    @ManyToOne
    @JoinColumn(name = "usuario_idadmin")
    private Usuario usuario;

    private byte[] imagenpubli;
    private String imagennombrepubli;
    private String imagencontenttypepubli;


    public int getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(int idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public byte[] getImagenpubli() {
        return imagenpubli;
    }

    public void setImagenpubli(byte[] imagenpubli) {
        this.imagenpubli = imagenpubli;
    }

    public String getImagennombrepubli() {
        return imagennombrepubli;
    }

    public void setImagennombrepubli(String imagennombrepubli) {
        this.imagennombrepubli = imagennombrepubli;
    }

    public String getImagencontenttypepubli() {
        return imagencontenttypepubli;
    }

    public void setImagencontenttypepubli(String imagencontenttypepubli) {
        this.imagencontenttypepubli = imagencontenttypepubli;
    }
}
