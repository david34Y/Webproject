package com.example.webproject.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Plantas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idplantas")
    private int idplantas;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "precio")
    private double precio;
    @Basic
    @Column(name = "recomendacion")
    private String recomendacion;
    @Basic
    @Column(name = "stock")
    private int stock;
    @Basic
    @Column(name = "imagen")
    private byte[] imagen;
    @Basic
    @Column(name = "imagencontenttype")
    private String imagencontenttype;
    @Basic
    @Column(name = "imagennombre")
    private String imagennombre;


    public int getIdplantas() {
        return idplantas;
    }

    public void setIdplantas(int idplantas) {
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagencontenttype() {
        return imagencontenttype;
    }

    public void setImagencontenttype(String imagencontenttype) {
        this.imagencontenttype = imagencontenttype;
    }

    public String getImagennombre() {
        return imagennombre;
    }

    public void setImagennombre(String imagennombre) {
        this.imagennombre = imagennombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plantas plantas = (Plantas) o;
        return idplantas == plantas.idplantas && Double.compare(plantas.precio, precio) == 0 && stock == plantas.stock && Objects.equals(nombre, plantas.nombre) && Objects.equals(recomendacion, plantas.recomendacion) && Arrays.equals(imagen, plantas.imagen) && Objects.equals(imagencontenttype, plantas.imagencontenttype) && Objects.equals(imagennombre, plantas.imagennombre);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idplantas, nombre, precio, recomendacion, stock, imagencontenttype, imagennombre);
        result = 31 * result + Arrays.hashCode(imagen);
        return result;
    }



}
