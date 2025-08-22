package com.proyecto.gymtracker.repository;

import com.proyecto.gymtracker.model.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento,Integer> {
}
