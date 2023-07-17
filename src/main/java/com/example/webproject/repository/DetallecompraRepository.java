package com.example.webproject.repository;

import com.example.webproject.entity.Detallecompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface DetallecompraRepository extends JpaRepository<Detallecompra, Integer> {
    @Query(value = "SELECT * FROM detallecompra WHERE plantas_idplantas = ?1",
    nativeQuery = true)
    List<Detallecompra> findByPlantasID(Integer id);

    @Query(value = "select * from detallecompra dc join compra co on dc.compra_idcompra = co.idcompra\n" +
            "join usuario u on co.usuario_idusuario = u.idusuario join plantas p on dc.plantas_idplantas = p.idplantas where u.idusuario = ?1", nativeQuery = true)
    List<Detallecompra> findByUserID(Integer id);

    @Query(value = "SELECT * FROM detallecompra WHERE compra_idcompra = ?1",
            nativeQuery = true)
    List<Detallecompra> findByComprasID(Integer id);

    @Query(value = "SELECT * FROM detallecompra WHERE compra_idcompra = ?1",
            nativeQuery = true)
    List<Detallecompra> findByCompraID(Integer idcompra);


    @Query(value = "SELECT iddetallecompra, cantidad,preciocompra,plantas_idplantas,compra_idcompra\n" +
            "FROM detallecompra dc\n" +
            "JOIN compra c ON dc.compra_idcompra = c.idcompra\n" +
            "WHERE c.estado = 'Completa';",nativeQuery = true)
    List<Detallecompra> findByCompraCompletada();


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO detallecompra ( `cantidad`, `preciocompra`, `plantas_idplantas`, `compra_idcompra`) " +
            "VALUES (?1, ?2, ?3, ?4);", nativeQuery = true)
    void insertDetalleCompra(int cantidad, double preciocompra, int plantas_idplantas, int compra_idcompra );



}
