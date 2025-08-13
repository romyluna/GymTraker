package com.proyecto.gymtracker.service.impl;

import com.proyecto.gymtracker.model.GrupoMuscular;
import com.proyecto.gymtracker.repository.GrupoMuscularRepository;
import com.proyecto.gymtracker.service.GrupoMuscularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoMuscularImpl implements GrupoMuscularService {

    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;

    @Override
    public List<GrupoMuscular> findAll(){
        return grupoMuscularRepository.findAll();
    }

    @Override
    public Optional<GrupoMuscular> findById(int gm_id){
        return grupoMuscularRepository.findById(gm_id);
    }

    @Override
    public GrupoMuscular save (GrupoMuscular grupo){
        return grupoMuscularRepository.save(grupo);
    }

    @Override
    public void deleteById(int gm_id){
        grupoMuscularRepository.deleteById(gm_id);
    }

    @Override
    public Optional<GrupoMuscular> findByNombre(String nombre) {
        return grupoMuscularRepository.findByNombre(nombre);
    }
}
