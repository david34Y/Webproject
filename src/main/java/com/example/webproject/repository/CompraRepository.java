package com.example.webproject.repository;

import com.example.webproject.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Compra c SET c.estado = 'Completa' WHERE c.idcompra = ?1",nativeQuery = true)
    void actualizarEstado(int id);
}
