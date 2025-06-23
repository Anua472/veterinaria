package com.ceatformacion.veterinaria.repository;

import com.ceatformacion.veterinaria.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository <Historial,Integer>{



    List<Historial> findByMascotaIdMascota(Integer idMascota);

}
