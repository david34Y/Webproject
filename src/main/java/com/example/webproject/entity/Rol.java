package com.example.webproject.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;


    private String nombrerol;

    // Constructor vacío
    public Rol() {}

    // Constructor con todos los campos
    public Rol(String nombreRol) {
        this.nombrerol = nombreRol;
    }

    // Getters y Setters
    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idRol) {
        this.idrol = idRol;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombreRol) {
        this.nombrerol = nombreRol;
    }

    // Método toString para imprimir el objeto
    @Override
    public String toString() {
        return "Rol [idRol=" + idrol + ", nombreRol=" + nombrerol + "]";
    }

}