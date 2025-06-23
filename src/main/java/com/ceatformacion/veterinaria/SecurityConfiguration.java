package com.ceatformacion.veterinaria;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //configurar la página que según
        http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.GET,"/","/index","/img**","script/++","css/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/crud").hasAnyRole("Admin","User")
                .requestMatchers(HttpMethod.POST,"/crud").hasRole("Admin")
                .requestMatchers(HttpMethod.GET,"/altaUsuario","/formulario").hasRole("Admin")
                .requestMatchers(HttpMethod.POST,"/guardarUsuario").hasRole("Admin")
                .requestMatchers("/editar/**","/borrar/**").hasRole("Admin")
                .anyRequest().authenticated()
        ).formLogin(form->form.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/crud",true)
                .permitAll()
        ).logout(LogoutConfigurer::permitAll);
        return http.build();


    }
    //encripta y lee las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //expone un objeto de Spring para usar internamente para autenticar usuarios, y lo hace accesible para que el programador lo pueda usar también
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)  throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
