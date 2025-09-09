package com.proyecto.gymtracker.service.impl;


import com.proyecto.gymtracker.model.Role;
import com.proyecto.gymtracker.model.Usuario;
import com.proyecto.gymtracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsuarioServiceImpl {

//REGISTRO DE USUARIO

    @Autowired
    private UsuarioRepository usuarioRepository;
/*
    @Autowired
    private PasswordEncoder passwordEncoder;
*/
    public Usuario registrarUsuario (String username, String password){

        //aca tendria que agregarle algo de que si el usuario existe no me deje agregarlo
        if(usuarioRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("El usuario ya existe");
        }

           Usuario user = new Usuario();
           user.setUsername(username);
           user.setPassword(password);
           //user.setPassword(passwordEncoder.encode(password));
           user.setRol(Role.ROLE_USUARIO); // <- esto ya esta seteando como "usuario" el rol del que se registra desde la web
           return usuarioRepository.save(user);
    }


    public Usuario buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username).orElse(null);
    }


}
