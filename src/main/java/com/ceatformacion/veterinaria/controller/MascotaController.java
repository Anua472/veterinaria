package com.ceatformacion.veterinaria.controller;

import com.ceatformacion.veterinaria.model.Mascota;
import com.ceatformacion.veterinaria.repository.MascotaRepository;
import com.ceatformacion.veterinaria.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MascotaController {
    @Autowired
    private MascotaRepository mascotasRepository;
    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/formulario")
    public String mostrarformulario(Model model) {
        //enviamos un objeto tipo client para que lo reciba el formulario
        model.addAttribute("mascota", new Mascota());
        return "formulario";
    }
    @PostMapping("/crud")
    public String leerFormulario(@ModelAttribute Mascota mascotaForm, Model model) {
        mascotasRepository.save(mascotaForm);
        return "redirect:/crud";
    }
//    @GetMapping("/crud")
//    public String mostrarMascota(Model model) {
//        model.addAttribute("mascotaParaCrud", mascotasRepository.findAll());
//        return "crud";
//    }
    @GetMapping("/editar/{idMascota}")
    public String editarMascota(@PathVariable int idMascota, Model model) {
        Mascota mascota = mascotasRepository.findById(idMascota).get();
        model.addAttribute("mascota", mascota);
        return "formulario";
    }
    @GetMapping("/borrar/{idMascota}")
    public String borrarMascota(@PathVariable int idMascota, Model model) {
        mascotasRepository.deleteById(idMascota);
        return "redirect:/crud";
    }
    @GetMapping("/crud")
    public String verMascota(@RequestParam(defaultValue = "0") int page,  Model model) {
        Page<Mascota> mascotasPage = mascotaService.listarMascotas(PageRequest.of(page, 5));
        model.addAttribute("mascotaParaCrud", mascotasPage.getContent());
        model.addAttribute("totalPages",mascotasPage.getTotalPages());
        model.addAttribute("currentPage",page);

        return "crud";

    }




}
