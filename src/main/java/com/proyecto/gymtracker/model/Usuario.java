package com.proyecto.gymtracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role rol;

}
