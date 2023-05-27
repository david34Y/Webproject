package com.example.webproject.repository;


import com.example.webproject.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query(value="select * from rol where idrol=?1", nativeQuery = true)
    List<Rol> findrol(Integer id);
}
