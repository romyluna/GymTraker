package com.proyecto.gymtracker.service;

import com.proyecto.gymtracker.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> findByusername (String username);
}
