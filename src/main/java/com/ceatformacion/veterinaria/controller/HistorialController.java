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
    @Autowired
    private HistorialRepository historialRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    private HistorialService historialService;
    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/historial/{idHistorial}")
    public String getHistorialById(@PathVariable int idHistorial){
        historialRepository.findById(idHistorial).get();
        return "historial";

    }
    @ResponseBody
    @PostMapping("api/historial")
    public Historial save(@RequestBody Historial historial){
        return historialRepository.save(historial);
    }
    
    //este método busca el historial de la mascota cuando se solicite por id
    @ResponseBody
    @GetMapping("/mascota/{idMascota}")
    public List<Historial> findByMascotaId(@PathVariable int idMascota){
        return historialService.obtenerHistorialXmascota(idMascota);
    }
    
    //controlar la visita del formulario e historial
    @GetMapping("/consulta/{id}")
    public String getHistorialById(@PathVariable int idHistorial, Model model){
        Mascota mascota = mascotaRepository.findById(idHistorial).orElseThrow();
        List<Historial> historial = historialRepository.findByMascotaIdMascota(idHistorial);
        model.addAttribute("mascota", mascota);
        model.addAttribute("historial", historial);
        model.addAttribute("nuevaVisita", new Historial());
        return "historial";
    }
    @PostMapping("/guardar")
    public String registrarVisita(@PathVariable int idMascota,@ModelAttribute("nuevaVisita") Historial nuevaVisita){
        Mascota mascota = mascotaRepository.findById(idMascota).orElseThrow();
        nuevaVisita.setMascota(mascota);
        historialRepository.save(nuevaVisita);
        return "redirect:/consulta/"+idMascota;
    }


}
