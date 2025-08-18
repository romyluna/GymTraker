package com.proyecto.gymtracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class GrupoMuscularPostDTO {

    private String nombre;
    private List<String> ejercicios;

}
