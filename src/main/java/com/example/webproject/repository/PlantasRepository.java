package com.example.plantas1.repository;

import com.example.plantas1.entity.Plantas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantasRepository extends JpaRepository<Plantas, Integer> {
    @Query(value="select * from plantas", nativeQuery = true)
    List<Plantas> plantas();


}
