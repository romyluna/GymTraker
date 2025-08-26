package com.proyecto.gymtracker.Controller;


import com.proyecto.gymtracker.dto.EntrenamientoDTO;
import com.proyecto.gymtracker.dto.EntrenamientoUpdateDTO;
import com.proyecto.gymtracker.model.Entrenamiento;
import com.proyecto.gymtracker.repository.EntrenamientoRepository;
import com.proyecto.gymtracker.repository.GrupoMuscularRepository;
import com.proyecto.gymtracker.service.EntrenamientoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrenamiento")
public class EntrenamientoController {


     EntrenamientoRepository entrenamientoRepository;
    @Autowired
    private EntrenamientoService entrenamientoService;



    @PostMapping
    public EntrenamientoDTO crearEntrenamiento(@RequestBody EntrenamientoDTO dto) {
        return entrenamientoService.crearEntrenamiento(dto);
    }



    /*
    // DELETE /entrenamientos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEntrenamiento(@PathVariable Integer id) {
        entrenamientoService.eliminarEntrenamiento(id);
        return ResponseEntity.ok("Entrenamiento eliminado correctamente");
    }

     */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEntrenamiento(@PathVariable Integer id) {
       try {
           entrenamientoService.eliminarEntrenamiento(id);
           return ResponseEntity.ok("Entrenamiento eliminado correctamente");
       } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("No se eliminó: " + e.getMessage());
       }

    }

    @PutMapping("/entrenamientos")
    public ResponseEntity<?> actualizar(@RequestBody EntrenamientoUpdateDTO dto) {

        try {
            return ResponseEntity.ok(entrenamientoService.actualizarEntrenamiento(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar: " + e.getMessage());
        }






    }


}
