package com.example.webproject.repository;

import com.example.webproject.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    @Query(value = "SELECT * FROM usuario WHERE RolID = 2",
            nativeQuery = true)
    List<Usuario> findAllClients();

    @Query(value = "SELECT * FROM usuario WHERE RolID = 1",
            nativeQuery = true)
    List<Usuario> findAllManagers();


    @Query(value = "SELECT * FROM usuario WHERE RolID = 2 AND (nombre = ?1 or apellido = ?1)",
            nativeQuery = true)
    List<Usuario> findSpecificClient(String nombre);

}
