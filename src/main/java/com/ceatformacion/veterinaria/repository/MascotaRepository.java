package com.ceatformacion.veterinaria.repository;

import com.ceatformacion.veterinaria.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    //esta interface conecta nuestra aplicación con la base de datos
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);

}
