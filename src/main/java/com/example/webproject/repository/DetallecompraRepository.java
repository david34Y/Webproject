package com.example.webproject.repository;

import com.example.webproject.entity.Detallecompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DetallecompraRepository extends JpaRepository<Detallecompra, Integer> {
    @Query(value = "SELECT * FROM detallecompra WHERE PlantasID = ?1",
    nativeQuery = true)
    List<Detallecompra> findByPlantasID(Integer id);

    @Query(value = "SELECT * FROM detallecompra WHERE CompraID = ?1",
            nativeQuery = true)
    List<Detallecompra> findByComprasID(Integer id);

    @Query(value = "SELECT iddetallecompra, cantidad,preciocompra,PlantasID,CompraID\n" +
            "FROM detallecompra dc\n" +
            "JOIN compra c ON dc.CompraID = c.idcompra\n" +
            "WHERE c.estado = 'Completa';",nativeQuery = true)
    List<Detallecompra> findByCompraCompletada();


}
