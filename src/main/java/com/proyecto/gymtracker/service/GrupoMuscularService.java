package com.proyecto.gymtracker.service;

import com.proyecto.gymtracker.dto.GrupoMuscularDTO;
import com.proyecto.gymtracker.dto.GrupoMuscularLightDTO;
import com.proyecto.gymtracker.dto.GrupoMuscularPostDTO;
import com.proyecto.gymtracker.model.GrupoMuscular;

import java.util.List;
import java.util.Optional;

public interface GrupoMuscularService {

    List<GrupoMuscularDTO> findAll();
    GrupoMuscularDTO findById(int id);
    GrupoMuscularPostDTO save(GrupoMuscularPostDTO grupo);
    void deleteById(int id);
    GrupoMuscularDTO  findByNombre(String nombre);
    GrupoMuscularPostDTO update (int id, GrupoMuscularPostDTO grupo);
    List<GrupoMuscularLightDTO> findAllGrupoMuscular();
}
