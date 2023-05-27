package com.example.webproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcompra;

    private byte estado;

    private int numplantas;
    private double monto;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Plantas plantas;

    // Constructor vacío
    public Compra() {}

    // Constructor con todos los campos
    public Compra(byte estado, int numPlantas, Usuario usuario, Plantas plantas) {
        this.estado = estado;
        this.numplantas = numPlantas;
        this.usuario = usuario;
        this.plantas = plantas;
    }

    // Getters y Setters
    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public int getNumplantas() {
        return numplantas;
    }

    public void setNumplantas(int numplantas) {
        this.numplantas = numplantas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plantas getPlantas() {
        return plantas;
    }

    public void setPlantas(Plantas plantas) {
        this.plantas = plantas;
    }

    // Método toString para imprimir el objeto
    @Override
    public String toString() {
        return "Compra [idCarrito=" + idcompra + ", estado=" + estado + ", numPlantas=" + numplantas + ", usuario=" + usuario + ", plantas=" + plantas + "]";
    }

}
