package com.proyecto.gymtracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class GrupoMuscularDTO {

    private int gm_id;
    private String nombre;
    private List<String> ejercicios;
}
