package com.example.webproject.repository;

import com.example.webproject.entity.Plantas;
import com.example.webproject.entity.Publicacion;
import com.example.webproject.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    @Query(value="select * from publicacion", nativeQuery = true)
    List<Publicacion> publicaciones();

    @Query(value="select * from publicacion where idpublicacion=?1", nativeQuery = true)
    List<Publicacion> findpubli(Integer id);

}
