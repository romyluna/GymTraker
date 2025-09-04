package com.proyecto.gymtracker.Controller;

import com.proyecto.gymtracker.dto.EjercicioLightDTO;
import com.proyecto.gymtracker.service.EjercicioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;


    @GetMapping("/ejercicios")
    @Operation(summary= "USUARIO: Trae solo los ejercicios que existen")
    public List<EjercicioLightDTO> findAllEjercicio() {
        return ejercicioService.findAllEjercicio();
    }


}
