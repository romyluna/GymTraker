package com.proyecto.gymtracker.Controller;

import com.proyecto.gymtracker.dto.GrupoMuscularDTO;
import com.proyecto.gymtracker.model.GrupoMuscular;
import com.proyecto.gymtracker.service.GrupoMuscularService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public GrupoMuscular create(@RequestBody GrupoMuscular grupo) {
        return grupoMuscularService.save(grupo);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<GrupoMuscular> update(@PathVariable int id, @RequestBody GrupoMuscular grupo) {
        return grupoMuscularService.findById(id)
                .map(g -> {
                    g.setNombre(grupo.getNombre());
                    return ResponseEntity.ok(grupoMuscularService.save(g));
                })
                .orElse(ResponseEntity.notFound().build());
    }

     */

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
