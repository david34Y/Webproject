package com.example.webproject.repository;

import com.example.webproject.entity.Plantas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantasRepository extends JpaRepository<Plantas, Integer> {
    List<Plantas> findByNombre(String nombre);
}
