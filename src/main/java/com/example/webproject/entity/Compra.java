package com.example.webproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcompra;

    private String estado;

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
        this.numplantas = num_plantas;
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

    private byte[] imagen;
    private String imagennombre;
    private String imagencontenttype;

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagennombre() {
        return imagennombre;
    }

    public void setImagennombre(String imagennombre) {
        this.imagennombre = imagennombre;
    }

    public String getImagencontenttype() {
        return imagencontenttype;
    }

    public void setImagencontenttype(String imagencontenttype) {
        this.imagencontenttype = imagencontenttype;
    }




}
