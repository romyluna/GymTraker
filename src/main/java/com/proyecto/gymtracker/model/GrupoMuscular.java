package com.proyecto.gymtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="GrupoMuscular")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoMuscular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gm_id;

    @Column(nullable = false, unique = true)
    private String nombre;

    //un grupo muscular puede tener muchos ejercicios
    //utilizo cascade porque al actualizas o eliminas un GrupoMuscular, JPA también hará esa acción sobre todos los Ejercicio de la lista automáticamente
    @OneToMany(mappedBy = "grupoMuscular", cascade = CascadeType.ALL)
    private List<Ejercicio> ejercicios;

}
