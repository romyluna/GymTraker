package com.proyecto.gymtracker.service;

import com.proyecto.gymtracker.dto.EjercicioLightDTO;


import java.util.List;


public interface EjercicioService {

    List<EjercicioLightDTO> findAllEjercicio();
}
