package com.proyecto.gymtracker.service;

import com.proyecto.gymtracker.model.GrupoMuscular;

import java.util.List;
import java.util.Optional;

public interface GrupoMuscularService {

    List<GrupoMuscular> findAll();
    Optional<GrupoMuscular> findById(int id);
    GrupoMuscular save(GrupoMuscular grupo);
    void deleteById(int id);
    Optional<GrupoMuscular> findByNombre(String nombre);
   // Optional<GrupoMuscular> findByNombre(String nombre);
}
