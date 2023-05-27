package com.example.plantas1.entity;
import jakarta.persistence.*;

import java.util.concurrent.CompletionStage;

@Entity
@Table(name="detallecompra")
public class DetalleCompra {
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
