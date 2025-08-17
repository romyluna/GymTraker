package com.proyecto.gymtracker.seed;

import com.proyecto.gymtracker.model.Ejercicio;
import com.proyecto.gymtracker.model.GrupoMuscular;
import com.proyecto.gymtracker.repository.EjercicioRepository;
import com.proyecto.gymtracker.repository.GrupoMuscularRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Value;
import java.util.List;


@Component
public class DataSeed implements CommandLineRunner{

    //para que corra por primera vez o no hay que modificar true o false en application properties -->(seed.enabled=true)
    @Value("${seed.enabled}")
    private boolean seedEnabled;

    private final GrupoMuscularRepository grupoMuscularRepository;
    private final EjercicioRepository ejercicioRepository;

    public DataSeed(GrupoMuscularRepository grupoMuscularRepository, EjercicioRepository ejercicioRepository) {
        this.grupoMuscularRepository = grupoMuscularRepository;
        this.ejercicioRepository = ejercicioRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Lista de grupos musculares

            List<String> grupos = List.of(
                    "Piernas",
                    "Pecho",
                    "Espalda",
                    "Hombros",
                    "Bíceps",
                    "Tríceps",
                    "Abdominales",
                    "Glúteos"
            );


        // Crear grupos si no existen
        for (String nombre : grupos) {
            grupoMuscularRepository.findByNombre(nombre)
                    .orElseGet(() -> grupoMuscularRepository.save(new GrupoMuscular(nombre)));
        }

        System.out.println("Iniciando DataSeed...");

        // Traer grupos para asignar ejercicios
        GrupoMuscular piernas = grupoMuscularRepository.findByNombre("Piernas").orElseThrow();
        GrupoMuscular pecho = grupoMuscularRepository.findByNombre("Pecho").orElseThrow();
        GrupoMuscular espalda = grupoMuscularRepository.findByNombre("Espalda").orElseThrow();
        GrupoMuscular hombros = grupoMuscularRepository.findByNombre("Hombros").orElseThrow();
        GrupoMuscular biceps = grupoMuscularRepository.findByNombre("Bíceps").orElseThrow();
        GrupoMuscular triceps = grupoMuscularRepository.findByNombre("Tríceps").orElseThrow();
        GrupoMuscular abdominales = grupoMuscularRepository.findByNombre("Abdominales").orElseThrow();
        GrupoMuscular gluteos = grupoMuscularRepository.findByNombre("Glúteos").orElseThrow();

        // Lista de ejercicios con grupo asociado
        List<Ejercicio> ejercicios = List.of(
                // Piernas
                new Ejercicio( "Sentadillas", piernas),
                new Ejercicio( "Prensa de piernas", piernas),
                new Ejercicio( "Extensiones de piernas", piernas),
                new Ejercicio( "Curl femoral", piernas),

                // Pecho
                new Ejercicio("Press de banca", pecho),
                new Ejercicio( "Flexiones", pecho),
                new Ejercicio( "Aperturas con mancuernas", pecho),

                // Espalda
                new Ejercicio( "Dominadas", espalda),
                new Ejercicio( "Remo con barra", espalda),
                new Ejercicio( "Jalón al pecho", espalda),

                // Hombros
                new Ejercicio( "Press militar", hombros),
                new Ejercicio( "Elevaciones laterales", hombros),

                // Bíceps
                new Ejercicio( "Curl con barra", biceps),
                new Ejercicio( "Curl con mancuernas", biceps),

                // Tríceps
                new Ejercicio( "Fondos en paralelas", triceps),
                new Ejercicio( "Extensión de tríceps", triceps),

                // Abdominales
                new Ejercicio("Crunch toco talon", abdominales),
                new Ejercicio("Crunch alternado", abdominales),
                new Ejercicio("Crunch completos", abdominales),
                new Ejercicio("Crunch manos al costado", abdominales),
                new Ejercicio("Elevacion de piernas alternada con mancuerna", abdominales),
                new Ejercicio("Plancha", abdominales),
                new Ejercicio("Plancha lateral", abdominales),
                new Ejercicio("Plancha recta con disco", abdominales),
                new Ejercicio("Plancha saco pie al costado", abdominales),
                new Ejercicio("cortitos", abdominales),
                new Ejercicio("cortitos con mancuernas", abdominales),
                new Ejercicio("V-ups alternados", abdominales),
                new Ejercicio("Montain Climbers", abdominales),
                new Ejercicio("Bicho muerto", abdominales),

                // Glúteos
                new Ejercicio( "Puente de glúteos", gluteos),
                new Ejercicio( "Patadas de glúteos", gluteos),
                new Ejercicio( "Hip trust", gluteos)

        );

        // Insertar ejercicios si no existen
        for (Ejercicio e : ejercicios) {
            boolean existe = ejercicioRepository.existsByNombre(e.getNombre());
            if (!existe) {
                ejercicioRepository.save(e);
                System.out.println("Ejercicio creado: " + e.getNombre());
            }
        }
    }
}
