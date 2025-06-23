package com.ceatformacion.veterinaria.controller;

import com.ceatformacion.veterinaria.model.Mascota;
import com.ceatformacion.veterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MascotaController {
    @Autowired
    private MascotaRepository mascotasRepository;

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
    @GetMapping("/crud")
    public String mostrarMascota(Model model) {
        model.addAttribute("mascotaParaCrud", mascotasRepository.findAll());
        return "crud";
    }
    @GetMapping("/editar/{id}")
    public String editarMascota(@PathVariable int id, Model model) {
        Mascota mascota = mascotasRepository.findById(id).get();
        model.addAttribute("mascota", mascota);
        return "formulario";
    }
    @GetMapping("/borrar/{id}")
    public String borrarMascota(@PathVariable int id, Model model) {
        mascotasRepository.deleteById(id);
        return "redirect:/crud";
    }
    @GetMapping("/buscar")
    public String buscarMascota(String nombre, Model model) {
        List<Mascota> resultado = mascotasRepository.findByNombreContainingIgnoreCase(nombre);
        model.addAttribute("mascotaParaCrud",resultado);

        return "crud";

    }




}
