package com.proyecto.gymtracker.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EntrenamientoUpdateDTO {

    private int entrenam_id; // ID del entrenamiento a actualizar
    private Date fecha;
    private String descripcion;
    private List<DetalleEntrenamientoUpdateDTO> detalles;

}
