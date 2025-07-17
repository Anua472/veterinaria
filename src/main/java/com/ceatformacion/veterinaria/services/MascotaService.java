package com.ceatformacion.veterinaria.services;

import com.ceatformacion.veterinaria.model.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface MascotaService {
    //metodos que se ejecutan desde el controlador
    Page<Mascota> listarMascotas(Pageable pageable);
}
