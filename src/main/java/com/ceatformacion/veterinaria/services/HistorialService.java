package com.ceatformacion.veterinaria.services;

import com.ceatformacion.veterinaria.model.Historial;
import com.ceatformacion.veterinaria.repository.HistorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {
    private HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
    }

    public List<Historial> obtenerHistorialPorMascota(Integer idMascota){
        return historialRepository.findByMascotaIdMascota(idMascota);

    }
    public Historial guardarEntrada(Historial historial){
        return historialRepository.save(historial);
    }
    public void eliminarEntrada(Integer idHistorial){
        historialRepository.deleteById(idHistorial);
    }
}
