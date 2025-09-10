package com.proyecto.gymtracker.Controller;

import com.proyecto.gymtracker.model.Usuario;
import com.proyecto.gymtracker.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrarUsuario(
                usuario.getUsername(),
                usuario.getPassword()
        );
        return ResponseEntity.ok(nuevo);
    }

}
