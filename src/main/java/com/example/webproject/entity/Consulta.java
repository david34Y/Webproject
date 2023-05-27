package com.example.webproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Entity
@Table(name="consulta")
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  idconsulta;

    @Column(nullable = false)
    @NotBlank(message = "Complete sus datos")
    private String asunto;

    @Column(nullable = false)
    @NotBlank(message = "Complete sus datos")
    private String contenido;

    @NotBlank(message = "Complete sus datos")
    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$",message = "Debe tener el formato nombre@correo.com")
    private String correocontacto;

    @NotBlank(message = "Complete sus datos")
    @Pattern(regexp = "[/^[A-Za-záéíñóúüÁÉÍÑÓÚÜ_.\\s]+$/g]{2,254}",message = "Solo puede ingresar letras")
    private String nombrecontacto;


    @ManyToOne
    @JoinColumn(name = "iduser")
    private Usuario user;

    public String getNombrecontacto() {
        return nombrecontacto;
    }

    public void setNombrecontacto(String nombrecontacto) {
        this.nombrecontacto = nombrecontacto;
    }

    public int getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(int idconsulta) {
        this.idconsulta = idconsulta;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getCorreocontacto() {
        return correocontacto;
    }

    public void setCorreocontacto(String correocontacto) {
        this.correocontacto = correocontacto;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
