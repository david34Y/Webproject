package com.example.webproject.repository;

import com.example.webproject.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuario WHERE rol_id = 2",
            nativeQuery = true)
    List<Usuario> findAllClients();

    @Query(value = "SELECT * FROM usuario WHERE rol_id = 1",
            nativeQuery = true)
    List<Usuario> findAllManagers();


    @Query(value = "SELECT * FROM usuario WHERE rol_id = 2 AND (nombre = ?1 or apellidos = ?1)",
            nativeQuery = true)
    List<Usuario> findSpecificClient(String nombre);

    List<Usuario> findUsuarioByCorreo(String correo);

    @Query(value = "SELECT * FROM usuario WHERE idusuario = ?1", nativeQuery = true)
    Usuario findUsuarioById(int id);

    @Query(value = "SELECT idusuario\n" +
            "FROM usuario\n" +
            "WHERE correo = ?1 AND password = ?2", nativeQuery = true)
    Integer loginUsuario(String correo, String password);

    @Query(value = "SELECT rol_id\n" +
            "FROM usuario\n" +
            "WHERE correo = ?1 AND password = ?2", nativeQuery = true)
    Integer obtenerRolID(String correo, String password);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO usuario (nombre, apellidos, correo, password, rol_id)\n" +
            "VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void registerUsuario(String nombre, String apellido, String email, String password, Integer idrol);

    @Transactional
    @Modifying
    @Query(value = "UPDATE usuario SET password = ?2 WHERE idusuario = ?1", nativeQuery = true)
    void cambiarcontrasena(Integer idusuario, String password);

}
