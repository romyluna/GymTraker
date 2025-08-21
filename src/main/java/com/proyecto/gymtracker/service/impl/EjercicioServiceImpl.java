package com.proyecto.gymtracker.service.impl;

import com.proyecto.gymtracker.dto.EjercicioLightDTO;
import com.proyecto.gymtracker.repository.EjercicioRepository;
import com.proyecto.gymtracker.service.EjercicioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EjercicioServiceImpl implements EjercicioService {


    @Autowired
    private EjercicioRepository ejercicioRepository;
    private final ModelMapper mapper;

    public EjercicioServiceImpl(EjercicioRepository ejercicioRepository, ModelMapper mapper) {
        this.ejercicioRepository = ejercicioRepository;
        this.mapper = mapper;
    }


    @Override
    public List<EjercicioLightDTO> findAllEjercicio() {
        return ejercicioRepository.findAll()
                .stream()
                .map(g -> {
                    EjercicioLightDTO dto = new EjercicioLightDTO();
                    dto.setNombre(g.getNombre());
                    return dto;
                })
                .toList();
    }


}
