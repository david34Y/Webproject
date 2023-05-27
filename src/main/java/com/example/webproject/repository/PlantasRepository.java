package com.example.webproject.repository;


import com.example.webproject.entity.Plantas;
import com.example.webproject.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantasRepository extends JpaRepository<Plantas, Integer> {
    @Query(value="select * from plantas", nativeQuery = true)
    List<Plantas> plantas();

    @Query(value="select * from plantas where idplantas=?1", nativeQuery = true)
    List<Plantas> findplants(Integer id);


}
