package com.proyecto.gymtracker.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EntrenamientoDTO {

    private Date fecha;
    private String descripcion;
    private List<DetalleEntrenamientoDTO> detalles;

}
