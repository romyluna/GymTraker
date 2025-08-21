package com.proyecto.gymtracker.repository;

import com.proyecto.gymtracker.dto.GrupoMuscularDTO;
import com.proyecto.gymtracker.model.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoMuscularRepository extends JpaRepository <GrupoMuscular,Integer> {

    Optional<GrupoMuscular> findByNombre(String nombre);



}
