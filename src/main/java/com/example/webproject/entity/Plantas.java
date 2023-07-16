package com.example.webproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name="plantas")
public class Plantas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idplantas;
    private String nombre;
    private double precio;
    private String recomendacion;
    private int stock;
    private byte[] imagen;
    private String imagennombre;
    private String imagencontenttype;

    private byte[] imagen2;
    private String imagennombre2;
    private String imagencontenttype2;

    private byte[] imagen3;
    private String imagennombre3;
    private String imagencontenttype3;

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagennombre2() {
        return imagennombre2;
    }

    public void setImagennombre2(String imagennombre2) {
        this.imagennombre2 = imagennombre2;
    }

    public String getImagencontenttype2() {
        return imagencontenttype2;
    }

    public void setImagencontenttype2(String imagencontenttype2) {
        this.imagencontenttype2 = imagencontenttype2;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagennombre3() {
        return imagennombre3;
    }

    public void setImagennombre3(String imagennombre3) {
        this.imagennombre3 = imagennombre3;
    }

    public String getImagencontenttype3() {
        return imagencontenttype3;
    }

    public void setImagencontenttype3(String imagencontenttype3) {
        this.imagencontenttype3 = imagencontenttype3;
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
