package com.ceatformacion.veterinaria.controller;

import com.ceatformacion.veterinaria.model.Usuario;
import com.ceatformacion.veterinaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder encode;

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

}
