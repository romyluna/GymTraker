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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public void deleteById(int gm_id){
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
