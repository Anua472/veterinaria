package com.ceatformacion.veterinaria.controller;

import com.ceatformacion.veterinaria.model.Historial;
import com.ceatformacion.veterinaria.model.Mascota;
import com.ceatformacion.veterinaria.repository.HistorialRepository;
import com.ceatformacion.veterinaria.repository.MascotaRepository;
import com.ceatformacion.veterinaria.services.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HistorialController {
    private final HistorialService historialService;
    //para conectar nuestra aplicación con la base de datos
    @Autowired
    private HistorialRepository historialRepository;
    //para conectar nuestra aplicación con la base de datos
    @Autowired
    private MascotaRepository mascotaRepository;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    // 🔄 API REST (JSON) para historial por ID de mascota
    @ResponseBody
    @GetMapping("/mascota/{idMascota}")
    public List<Historial> GetHistorialByMascotaId(@PathVariable int idMascota){
        return historialService.obtenerHistorialPorMascota(idMascota);
    }
    //API Rest para guardar una nueva entrada(Json)
    @ResponseBody
    @PostMapping("api/historial")
    public Historial guardar(@RequestBody Historial entrada){
        return historialService.guardarEntrada(entrada);
    }
    //Api Rest para eliminar
    @ResponseBody
    @DeleteMapping("api/historial/{idHistorial}")
    public void eliminar(@PathVariable int idHistorial){
        historialService.eliminarEntrada(idHistorial);
    }
    //API Rest para historial por ID de mascota
    @GetMapping("/buscar")
    public String buscarPorNombre(@RequestParam String nombre, Model model){
        List<Mascota> resultados = mascotaRepository.findByNombreContainingIgnoreCase(nombre);
        model.addAttribute("mascotaParaCrud", resultados);
        return "crud";

    }
    @GetMapping("/consulta/{idHistorial}")
    public String verConsultaMascota(@PathVariable int idHistorial, Model model){
        Mascota mascota = mascotaRepository.findById(idHistorial).orElseThrow();
        List<Historial> historial = historialRepository.findByMascotaIdMascota(idHistorial);
        model.addAttribute("mascota", mascota);
        model.addAttribute("historial", historial);
        model.addAttribute("nuevaVisita", new Historial());

        return "historial";

    }

    //Post desde formulario:registrar visita
    @PostMapping("/consulta/{idMascota}")
    public String registrarVisita(@PathVariable int idMascota,@ModelAttribute("nuevaVisita") Historial nuevaVisita){
        Mascota mascota = mascotaRepository.findById(idMascota).orElseThrow();
        nuevaVisita.setMascota(mascota);
        historialRepository.save(nuevaVisita);
        return "redirect:/consulta/"+idMascota;
    }


}
