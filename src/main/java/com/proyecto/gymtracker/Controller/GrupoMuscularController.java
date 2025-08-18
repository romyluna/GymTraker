package com.proyecto.gymtracker.Controller;

import com.proyecto.gymtracker.dto.GrupoMuscularDTO;
import com.proyecto.gymtracker.dto.GrupoMuscularPostDTO;
import com.proyecto.gymtracker.model.GrupoMuscular;
import com.proyecto.gymtracker.service.GrupoMuscularService;
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
    public ResponseEntity<GrupoMuscularPostDTO> create(@RequestBody GrupoMuscularPostDTO grupo) {
        try {
            GrupoMuscularPostDTO saved = grupoMuscularService.save(grupo); //guardo la respuesta que me da mi metodo
            return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // imprime "El grupo muscular ya existe" en consola
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict
        }
    }



    @PutMapping("/{id}")
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
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        grupoMuscularService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<GrupoMuscularDTO> findByNombre(@PathVariable String nombre) {
        return grupoMuscularService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
