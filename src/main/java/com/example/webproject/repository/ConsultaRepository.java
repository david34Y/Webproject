package com.example.webproject.repository;

import com.example.webproject.entity.Consulta;
import com.example.webproject.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

}
