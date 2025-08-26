package com.proyecto.gymtracker.service;

import com.proyecto.gymtracker.dto.EntrenamientoDTO;
import com.proyecto.gymtracker.dto.EntrenamientoUpdateDTO;
import com.proyecto.gymtracker.model.Entrenamiento;
import org.springframework.stereotype.Service;

@Service
public interface EntrenamientoService {

    EntrenamientoDTO crearEntrenamiento(EntrenamientoDTO dto);
    void eliminarEntrenamiento(int id);
    EntrenamientoUpdateDTO actualizarEntrenamiento(EntrenamientoUpdateDTO dto);
}
