package com.proyecto.gymtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Entrenamiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entrenam_id;

    @Column
    private Date fecha;

    @Column(length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "entrenamiento", cascade = CascadeType.ALL)
    private List<DetalleEntrenamiento> detalles;

}
