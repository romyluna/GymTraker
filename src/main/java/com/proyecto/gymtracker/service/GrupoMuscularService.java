package com.proyecto.gymtracker.service;

import com.proyecto.gymtracker.dto.GrupoMuscularDTO;
import com.proyecto.gymtracker.model.GrupoMuscular;

import java.util.List;
import java.util.Optional;

public interface GrupoMuscularService {

    List<GrupoMuscularDTO> findAll();
    Optional<GrupoMuscular> findById(int id);
    GrupoMuscular save(GrupoMuscular grupo);
    void deleteById(int id);
    Optional<GrupoMuscularDTO> findByNombre(String nombre);
   // Optional<GrupoMuscular> findByNombre(String nombre);
}
