package com.proyecto.gymtracker.dto;

import lombok.Data;

@Data
public class DetalleEntrenamientoDTO {

    private int ejercicioId;
    private int series;
    private int repeticiones;
    private Double peso;
}
