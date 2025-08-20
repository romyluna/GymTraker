package com.proyecto.gymtracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class GrupoMuscularPostDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotEmpty(message = "Debe tener al menos un ejercicio")
    private List<@NotBlank(message = "Cada ejercicio debe tener un nombre") String> ejercicios;

}
