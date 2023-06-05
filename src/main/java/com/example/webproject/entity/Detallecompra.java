package com.example.webproject.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Detallecompra {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iddetallecompra")
    private int iddetallecompra;

    @Basic
    @Column(name = "cantidad")
    private int cantidad;
    @Basic
    @Column(name = "preciocompra")
    private Double preciocompra;
    @ManyToOne
    @JoinColumn(name = "plantas_idplantas")
    private Plantas plantas;
    @ManyToOne
    @JoinColumn(name = "compra_idcompra")
    private Compra compra;


    public int getIddetallecompra() {
        return iddetallecompra;
    }

    public void setIddetallecompra(int iddetallecompra) {
        this.iddetallecompra = iddetallecompra;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(Double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public Plantas getPlantas() {
        return plantas;
    }

    public void setPlantas(Plantas plantas) {
        this.plantas = plantas;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

}
