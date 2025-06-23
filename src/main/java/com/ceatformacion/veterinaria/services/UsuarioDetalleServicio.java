package com.ceatformacion.veterinaria.services;

import com.ceatformacion.veterinaria.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//declara un servicio
@Service
public class UsuarioDetalleServicio implements UserDetailsService {
    UsuarioRepository usuarioRepository;

    public UsuarioDetalleServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //método que se ejecuta automaticamente cuando alguien intenta iniciar sesión
    //Spring llama este m para obtener los datos de usuario

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username).map(UsuarioDetalle::new).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
    }
}
