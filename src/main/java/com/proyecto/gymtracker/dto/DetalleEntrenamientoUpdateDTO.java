package com.proyecto.gymtracker.dto;

import lombok.Data;

@Data
public class DetalleEntrenamientoUpdateDTO {

    private int detent_id; // null si es un detalle nuevo
    private int ejercicio_id;
    private int series;
    private int repeticiones;
    private Double peso;
}
