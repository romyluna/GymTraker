package com.proyecto.gymtracker.service.impl;



import com.proyecto.gymtracker.dto.DetalleEntrenamientoUpdateDTO;
import com.proyecto.gymtracker.dto.EntrenamientoDTO;
import com.proyecto.gymtracker.dto.EntrenamientoUpdateDTO;
import com.proyecto.gymtracker.model.DetalleEntrenamiento;
import com.proyecto.gymtracker.model.Ejercicio;
import com.proyecto.gymtracker.model.Entrenamiento;
import com.proyecto.gymtracker.repository.DetalleEntrenamientoRepository;
import com.proyecto.gymtracker.repository.EjercicioRepository;
import com.proyecto.gymtracker.repository.EntrenamientoRepository;
import com.proyecto.gymtracker.service.EntrenamientoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EntrenamientoServiceImpl implements EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private DetalleEntrenamientoRepository detalleEntrenamientoRepository;

    private final ModelMapper mapper;

    public EntrenamientoServiceImpl(EntrenamientoRepository entrenamientoRepository, ModelMapper mapper) {
        this.entrenamientoRepository = entrenamientoRepository;
        this.mapper = mapper;
    }

//crear un nuevo entrenamiento

    public EntrenamientoDTO crearEntrenamiento(EntrenamientoDTO dto) {
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
         entrenamientoRepository.save(entrenamiento);
         return dto;
    }


    public void eliminarEntrenamiento(int id) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("el id " + id + " porque el entrenamiento no existe"));

        detalleEntrenamientoRepository.deleteAll(entrenamiento.getDetalles());
        entrenamientoRepository.delete(entrenamiento);
    }

//actualizacion de entrenamiento

    @Transactional
    public EntrenamientoUpdateDTO actualizarEntrenamiento(EntrenamientoUpdateDTO dto) {
        // 1. Buscar el entrenamiento existente
        Entrenamiento entrenamiento = entrenamientoRepository.findById(dto.getEntrenam_id())
                .orElseThrow(() -> new RuntimeException("Entrenamiento no encontrado"));

        // 2. Actualizar campos simples
        entrenamiento.setFecha(dto.getFecha());
        entrenamiento.setDescripcion(dto.getDescripcion());

        // 3. Crear un mapa con los detalles existentes para fácil búsqueda (CLAVE -VALOR) Recorro mas facil sin hacer un for
        Map<Integer, DetalleEntrenamiento> detallesExistentes = entrenamiento.getDetalles()
                .stream()
                .collect(Collectors.toMap(DetalleEntrenamiento::getDetent_id, d -> d));

        // 4. Nueva lista de detalles que quedarán después del update
        List<DetalleEntrenamiento> nuevosDetalles = new ArrayList<>();

        for (DetalleEntrenamientoUpdateDTO dDto : dto.getDetalles()) {
            if (dDto.getDetent_id() > 0) {
                // 4.a) Es un detalle existente → actualizar
                DetalleEntrenamiento detalle = detallesExistentes.remove(dDto.getDetent_id());
                if (detalle != null) {
                    detalle.setSeries(dDto.getSeries());
                    detalle.setRepeticiones(dDto.getRepeticiones());
                    detalle.setPeso(dDto.getPeso());
                    // Actualizamos ejercicio si cambió
                    if (detalle.getEjercicio().getEjercicio_id() != dDto.getEjercicio_id()) {
                        Ejercicio nuevoEjercicio = ejercicioRepository.findById(dDto.getEjercicio_id())
                                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));
                        detalle.setEjercicio(nuevoEjercicio);
                    }
                    nuevosDetalles.add(detalle);
                }
            } else {
                // 4.b) Es un detalle nuevo → crearlo
                DetalleEntrenamiento nuevo = new DetalleEntrenamiento();
                nuevo.setEntrenamiento(entrenamiento);
                nuevo.setSeries(dDto.getSeries());
                nuevo.setRepeticiones(dDto.getRepeticiones());
                nuevo.setPeso(dDto.getPeso());
                Ejercicio ejercicio = ejercicioRepository.findById(dDto.getEjercicio_id())
                        .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));
                nuevo.setEjercicio(ejercicio);
                nuevosDetalles.add(nuevo);
            }
        }

        // 5. Los que quedaron en detallesExistentes no están en el DTO → eliminarlos

        entrenamiento.getDetalles().clear(); // vaciamos la lista
        // 6. Setear la nueva lista de detalles
        entrenamiento.getDetalles().addAll(nuevosDetalles); // agregamos los nuevos/actualizados

        // 7. Guardar el entrenamiento actualizado
        //return entrenamientoRepository.save(entrenamiento);
        entrenamientoRepository.save(entrenamiento);
        return dto;

    }


}