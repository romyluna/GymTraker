package com.proyecto.gymtracker.service.impl;

import com.proyecto.gymtracker.dto.GrupoMuscularDTO;
import com.proyecto.gymtracker.dto.GrupoMuscularPostDTO;
import com.proyecto.gymtracker.model.Ejercicio;
import com.proyecto.gymtracker.model.GrupoMuscular;
import com.proyecto.gymtracker.repository.GrupoMuscularRepository;
import com.proyecto.gymtracker.service.GrupoMuscularService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GrupoMuscularImpl implements GrupoMuscularService {

    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;
    private final ModelMapper mapper;

    public GrupoMuscularImpl(GrupoMuscularRepository grupoMuscularRepository, ModelMapper mapper) {
        this.grupoMuscularRepository = grupoMuscularRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GrupoMuscularDTO> findAll() {
        return grupoMuscularRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(GrupoMuscular::getGm_id)) // para que me ordene de forma asc
                .map(g -> {
                    GrupoMuscularDTO dto = new GrupoMuscularDTO();
                    dto.setGm_id(g.getGm_id());
                    dto.setNombre(g.getNombre());
                    // Solo los nombres de los ejercicios
                    List<String> nombresEjercicios = g.getEjercicios()
                            .stream()
                            .map(Ejercicio::getNombre)
                            .toList();
                    dto.setEjercicios(nombresEjercicios);
                    return dto;
                })
                .toList();
    }

    @Override
    public Optional<GrupoMuscularDTO> findById(int gm_id){
        return grupoMuscularRepository.findById(gm_id)
                .map(grupo -> {
                    GrupoMuscularDTO dto = new GrupoMuscularDTO();
                    dto.setGm_id(grupo.getGm_id());
                    dto.setNombre(grupo.getNombre());
                    dto.setEjercicios(
                            grupo.getEjercicios()
                                    .stream()
                                    .map(Ejercicio::getNombre)
                                    .toList()
                    );
                    return dto;
                });
    }

    @Override
    public GrupoMuscularPostDTO save(GrupoMuscularPostDTO dto) {
        // Buscamos si ya existe un grupo con ese nombre

        if(grupoMuscularRepository.findByNombre(dto.getNombre()).isPresent()) {
            throw new IllegalArgumentException("El grupo muscular ya existe");//le avisa al controller que ya existe
        }
        //objeto que sí se persiste en la BD.
        GrupoMuscular grupo = new GrupoMuscular();


        //copio el nombre del DTO a la entidad
        grupo.setNombre(dto.getNombre());
        grupo.setEjercicios(dto.getEjercicios().stream()
                .map(nombre -> {
                    Ejercicio e = new Ejercicio();
                    e.setNombre(nombre);
                    e.setGrupoMuscular(grupo);
                    return e;
                }).toList());

        grupoMuscularRepository.save(grupo);
        return dto;
    }

    @Override
    public GrupoMuscularPostDTO update(int id, GrupoMuscularPostDTO dto) {
        GrupoMuscular grupo = grupoMuscularRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo muscular no encontrado"));

        grupo.setNombre(dto.getNombre());

        // Crear un Set con los nombres de los ejercicios existentes
        Set<String> nombresExistentes = grupo.getEjercicios().stream()
                .map(Ejercicio::getNombre)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        // Mapear los nombres del DTO a ejercicios, lanzando excepción si ya existen
        List<Ejercicio> ejerciciosActualizados = dto.getEjercicios().stream()
                .map(nombre -> {
                    if (nombresExistentes.contains(nombre.toLowerCase())) {
                        throw new IllegalArgumentException("El ejercicio '" + nombre + "' ya se encuentra en la base por lo tanto no se agrega ninguno de los ejercicio mencionados");
                    } else {
                        Ejercicio nuevo = new Ejercicio();
                        nuevo.setNombre(nombre);
                        nuevo.setGrupoMuscular(grupo);
                        return nuevo;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new)); // <-- mutable

        grupo.setEjercicios(ejerciciosActualizados);

        grupoMuscularRepository.save(grupo);

        return dto;
    }

    @Override
    public void deleteById(int gm_id){
        if (!grupoMuscularRepository.existsById(gm_id)) {
            throw new IllegalArgumentException(
                    "El grupo muscular con ID " + gm_id + " no existe en la base"
            );
        }
        grupoMuscularRepository.deleteById(gm_id);
    }

    @Override
    public Optional<GrupoMuscularDTO> findByNombre(String nombre) {
        return grupoMuscularRepository.findByNombre(nombre)
                .map(grupo -> {
                    GrupoMuscularDTO dto = new GrupoMuscularDTO();
                    dto.setGm_id(grupo.getGm_id());
                    dto.setNombre(grupo.getNombre());
                    dto.setEjercicios(
                            grupo.getEjercicios()
                                    .stream()
                                    .map(Ejercicio::getNombre)
                                    .toList()
                    );
                    return dto;
                });
    }

}
