package com.proyecto.gymtracker.Controller;


import com.proyecto.gymtracker.dto.EntrenamientoDTO;
import com.proyecto.gymtracker.model.Entrenamiento;
import com.proyecto.gymtracker.service.EntrenamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entrenamiento")
public class EntrenamientoController {


    @Autowired
    private EntrenamientoService entrenamientoService;

    @PostMapping
    public Entrenamiento crearEntrenamiento(@RequestBody EntrenamientoDTO dto) {
        return entrenamientoService.crearEntrenamiento(dto);
    }

}
