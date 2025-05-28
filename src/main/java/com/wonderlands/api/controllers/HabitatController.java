package com.wonderlands.api.controllers;

import com.wonderlands.api.entities.Habitat;
import com.wonderlands.api.services.HabitatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {

    @Autowired
    private HabitatService habitatService;

    // GET /api/habitats - Todos los hábitats
    @GetMapping
    public ResponseEntity<List<Habitat>> getAllHabitats() {
        return ResponseEntity.ok(habitatService.findAll());
    }

    // GET /api/habitats/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Habitat> getHabitatById(@PathVariable Long id) {
        return ResponseEntity.ok(habitatService.findById(id));
    }

    // POST /api/habitats - Crear nuevo
    @PostMapping
    public ResponseEntity<Habitat> createHabitat(@RequestBody Habitat habitat) {
        Habitat createdHabitat = habitatService.createHabitat(habitat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHabitat);
    }

    // PUT /api/habitats/{id} - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Habitat> updateHabitat(
            @PathVariable Long id,
            @RequestBody Habitat habitatDetails) {
        return ResponseEntity.ok(habitatService.updateHabitat(id, habitatDetails));
    }

    // DELETE /api/habitats/{id} - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitat(@PathVariable Long id) {
        habitatService.deleteHabitat(id);
        return ResponseEntity.noContent().build();
    }
}
