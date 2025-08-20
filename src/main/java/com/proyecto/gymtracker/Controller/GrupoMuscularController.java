package com.proyecto.gymtracker.Controller;

import com.proyecto.gymtracker.dto.GrupoMuscularDTO;
import com.proyecto.gymtracker.dto.GrupoMuscularPostDTO;
import com.proyecto.gymtracker.model.GrupoMuscular;
import com.proyecto.gymtracker.service.GrupoMuscularService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos-musculares")
public class GrupoMuscularController {

    @Autowired
    private GrupoMuscularService grupoMuscularService;

   @GetMapping
    public List<GrupoMuscularDTO> findAll() {
       return grupoMuscularService.findAll();
   }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoMuscularDTO> getById(@PathVariable int id) {
        return grupoMuscularService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary= "Crear un nuevo Grupo Muscular + Ejercicios", description = "En caso que el grupo muscular ya exista no lo va agregar")
    public ResponseEntity<?> create(@Valid @RequestBody GrupoMuscularPostDTO grupo) {
        try {
            GrupoMuscularPostDTO saved = grupoMuscularService.save(grupo); //guardo la respuesta que me da mi metodo
            return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201
        } catch (IllegalArgumentException e) {
            //System.out.println(e.getMessage()); // imprime "El grupo muscular ya existe" en consola
            return ResponseEntity.status(HttpStatus.CONFLICT).body("UPS! " + e.getMessage()); // 409 Conflict
        }
    }

    @PutMapping("/{id}")
    @Operation(summary= "Modificar Grupo Muscular o No + agregar nuevos ejercicios", description = "En caso que un ejercicio ya exista no se agrega ningun ejercicio a la base")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody GrupoMuscularPostDTO grupo) {
        try {
            GrupoMuscularPostDTO updated = grupoMuscularService.update(id, grupo);
            return ResponseEntity.ok(updated); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage()); // devuelve solo el mensaje
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary= "Eliminación Grupo muscular + Ejercicios", description = "Si se borra un grupo muscular se borran todos sus ejercicios")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        try {
            grupoMuscularService.deleteById(id); // lógica del service
            return ResponseEntity.ok("El grupo muscular con ID " + id + " se eliminó correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se eliminó: " + e.getMessage());
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<GrupoMuscularDTO> findByNombre(@PathVariable String nombre) {
        return grupoMuscularService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
