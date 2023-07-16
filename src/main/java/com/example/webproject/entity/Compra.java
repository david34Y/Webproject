package com.example.webproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcompra;

    private String estado;

    private int num_plantas;
    private double monto;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @ManyToOne
    private Usuario usuario;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Constructor vacío
    public Compra() {}



    // Constructor con todos los campos


    public Compra(int idcompra, String estado, int num_plantas, double monto, Usuario usuario) {
        this.idcompra = idcompra;
        this.estado = estado;
        this.num_plantas = num_plantas;
        this.monto = monto;
        this.usuario = usuario;
    }

    // Getters y Setters
    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }



    public int getNumplantas() {
        return num_plantas;
    }

    public void setNumplantas(int numplantas) {
        this.num_plantas = numplantas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}
