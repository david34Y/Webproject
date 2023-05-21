package com.example.webproject.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Rol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idestado")
    private int idestado;
    @Basic
    @Column(name = "nombre_rol")
    private String nombreRol;



    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return idestado == rol.idestado && Objects.equals(nombreRol, rol.nombreRol);
    }

}
