package com.ceatformacion.veterinaria.controller;

import com.ceatformacion.veterinaria.model.Mascota;
import com.ceatformacion.veterinaria.model.Usuario;
import com.ceatformacion.veterinaria.repository.UsuarioRepository;
import com.ceatformacion.veterinaria.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder encode;
    MascotaService mascotaService;

    @GetMapping("/altaUsuario")
    public String altaUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "altaUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername(usuario.getUsername());
            user.setPassword(encode.encode(usuario.getPassword()));
            user.setRol(usuario.getRol());
            usuarioRepository.save(user);
            return "redirect:/";
        }else{
            model.addAttribute("error","Usuario ya existente");
            return "altaUsuario";
        }


    }
    //paginación
    @GetMapping("/crud-user")
    public String verMascotas(@RequestParam(defaultValue = "0") int page, Model model){
        Page<Mascota> mascotasPage = mascotaService.listarMascotas(PageRequest.of(page, 5));
        model.addAttribute("mascotaParaCrud",mascotasPage.getContent());
        model.addAttribute("totalPages",mascotasPage.getTotalPages());
        model.addAttribute("currentPage",page);
        return "crud";
    }


}
