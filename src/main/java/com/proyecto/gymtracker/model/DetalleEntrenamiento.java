package com.proyecto.gymtracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="DetalleEntrenamiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detent_id;

    @ManyToOne
    @JoinColumn(name = "entrenam_id", nullable = false ) //nullable: porque un detalle siempre debe estar asociado a un entrenamiento y no puede ser null esta col
    private Entrenamiento entrenamiento;

    @ManyToOne
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    @Column(nullable = false)
    private int series;

    @Column(nullable = false)
    private int repeticiones;

    @Column(nullable = false)
    private Double peso;

}
