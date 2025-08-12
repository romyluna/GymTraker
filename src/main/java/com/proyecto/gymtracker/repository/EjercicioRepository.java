package com.proyecto.gymtracker.repository;

import com.proyecto.gymtracker.model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepository extends JpaRepository <Ejercicio,Integer> {

    boolean existsByNombre(String nombre);

}
