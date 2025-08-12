package com.proyecto.gymtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Ejercicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ejercicio_id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "gm_id" ,nullable = false) //nullable: porque un ejercicio siempre debe estar asociado a un grupo muscular
    private GrupoMuscular grupoMuscular;

}
