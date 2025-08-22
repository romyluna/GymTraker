package com.proyecto.gymtracker.service.impl;


import com.proyecto.gymtracker.dto.EntrenamientoDTO;
import com.proyecto.gymtracker.model.DetalleEntrenamiento;
import com.proyecto.gymtracker.model.Entrenamiento;
import com.proyecto.gymtracker.repository.EjercicioRepository;
import com.proyecto.gymtracker.repository.EntrenamientoRepository;
import com.proyecto.gymtracker.service.EntrenamientoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenamientoServiceImpl implements EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    private final ModelMapper mapper;

    public EntrenamientoServiceImpl(EntrenamientoRepository entrenamientoRepository, ModelMapper mapper) {
        this.entrenamientoRepository = entrenamientoRepository;
        this.mapper = mapper;
    }

//crear un nuevo entrenamiento

    public Entrenamiento crearEntrenamiento(EntrenamientoDTO dto) {
        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setFecha(dto.getFecha());
        entrenamiento.setDescripcion(dto.getDescripcion());

        List<DetalleEntrenamiento> detalles = dto.getDetalles().stream().map(d -> {
            DetalleEntrenamiento det = new DetalleEntrenamiento();
            det.setEntrenamiento(entrenamiento);//"pertenezco a esta carpeta de entrenamiento"

            det.setEjercicio(ejercicioRepository.findById(d.getEjercicioId())
                    .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado")));
            det.setSeries(d.getSeries());
            det.setRepeticiones(d.getRepeticiones());
            det.setPeso(d.getPeso());
            return det;
        }).toList();

        entrenamiento.setDetalles(detalles);
        return entrenamientoRepository.save(entrenamiento);
    }


}
