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

    public List<Historial> obtenerHistorialXmascota(int idMascota){
        return historialRepository.findByMascotaIdMascota(idMascota);

    }
    public Historial guardarHistorial(Historial historial){
        return historialRepository.save(historial);
    }
    public void borrarHistorialXmascota(int idHistorial){
        historialRepository.deleteById(idHistorial);
    }
}
