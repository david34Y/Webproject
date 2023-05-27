package com.example.webproject.repository;

import com.example.webproject.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findUsuarioByCorreo(String correo);



}
