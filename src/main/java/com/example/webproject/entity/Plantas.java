package com.example.plantas1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="plantas")
public class Plantas {
    @Id
    private Integer idplantas;
    private String nombre;
    private double precio;
    private String recomendacion;
    private int stock;
    private byte[] imagen;
    private String imagennombre;
    private String imagencontenttype;

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



    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Integer getIdplantas() {
        return idplantas;
    }

    public void setIdplantas(Integer idplantas) {
        this.idplantas = idplantas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
