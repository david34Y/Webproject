package com.example.webproject.entity;
import jakarta.persistence.*;

@Entity
@Table(name="detallecompra")
public class Detallecompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddetallecompra;

    private int cantidad;

    private double preciocompra;

    @ManyToOne
    @JoinColumn(name = "PlantasID")
    private Plantas plantas;

    @ManyToOne
    @JoinColumn(name = "CompraID")
    private Compra compra;


}
