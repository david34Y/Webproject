package com.example.webproject.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Compra {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcompra")
    private int idcompra;
    @Basic
    @Column(name = "numplantas")
    private int numplantas;
    @Basic
    @Column(name = "monto")
    private Double monto;
    @Basic
    @Column(name = "estado")
    private String estado;
    @ManyToOne
    @JoinColumn(name = "UsuarioID")
    private Usuario usuario;


    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getNumplantas() {
        return numplantas;
    }

    public void setNumplantas(int numplantas) {
        this.numplantas = numplantas;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
