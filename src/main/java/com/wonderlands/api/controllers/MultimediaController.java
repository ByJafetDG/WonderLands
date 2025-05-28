package com.wonderlands.api.controllers;

import com.wonderlands.api.entities.Multimedia;
import com.wonderlands.api.services.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multimedia")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    // GET /api/multimedia - Todos los recursos
    @GetMapping
    public ResponseEntity<List<Multimedia>> getAllMultimedia() {
        return ResponseEntity.ok(multimediaService.findAll());
    }

    // GET /api/multimedia/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Multimedia> getMultimediaById(@PathVariable Long id) {
        return ResponseEntity.ok(multimediaService.findById(id));
    }

    // GET /api/species/{speciesId}/multimedia - Multimedia por especie
    @GetMapping("/species/{speciesId}")
    public ResponseEntity<List<Multimedia>> getMultimediaBySpecies(@PathVariable Long speciesId) {
        return ResponseEntity.ok(multimediaService.findBySpeciesId(speciesId));
    }

    // POST /api/multimedia - Crear nuevo recurso
    @PostMapping
    public ResponseEntity<Multimedia> createMultimedia(@RequestBody Multimedia multimedia) {
        Multimedia createdMultimedia = multimediaService.createMultimedia(multimedia);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMultimedia);
    }

    // PUT /api/multimedia/{id} - Actualizar recurso
    @PutMapping("/{id}")
    public ResponseEntity<Multimedia> updateMultimedia(
            @PathVariable Long id,
            @RequestBody Multimedia multimediaDetails) {
        return ResponseEntity.ok(multimediaService.updateMultimedia(id, multimediaDetails));
    }

    // DELETE /api/multimedia/{id} - Eliminar recurso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMultimedia(@PathVariable Long id) {
        multimediaService.deleteMultimedia(id);
        return ResponseEntity.noContent().build();
    }
}