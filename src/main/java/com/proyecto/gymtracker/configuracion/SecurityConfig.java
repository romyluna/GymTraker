package com.proyecto.gymtracker.configuracion;

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

 */

//@EnableMethodSecurity //habilita @PreAuthorize, @PostAuthorize, @Secured
//@Configuration
public class SecurityConfig {

    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/usuario/**").hasAnyRole("USUARIO", "ADMINISTRADOR")
                        .anyRequest().authenticated()
                        //Todo lo demás necesita estar logueado, pero sin restricción de rol específico
                )
                .formLogin(form -> form.permitAll())
                .httpBasic(basic -> {}); // Forma actual sin deprecación

        return http.build();
    }

*/
}




