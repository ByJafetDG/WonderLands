package com.wonderlands.api.controllers;

import com.wonderlands.api.entities.ConservationStatus;
import com.wonderlands.api.services.ConservationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conservation-statuses")
public class ConservationStatusController {

    @Autowired
    private ConservationStatusService conservationStatusService;

    // GET /api/conservation-statuses - Todos los estados
    @GetMapping
    public ResponseEntity<List<ConservationStatus>> getAllConservationStatuses() {
        return ResponseEntity.ok(conservationStatusService.findAll());
    }

    // GET /api/conservation-statuses/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConservationStatus> getConservationStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(conservationStatusService.findById(id));
    }

    // POST /api/conservation-statuses - Crear nuevo
    @PostMapping
    public ResponseEntity<ConservationStatus> createConservationStatus(@RequestBody ConservationStatus conservationStatus) {
        ConservationStatus createdStatus = conservationStatusService.createConservationStatus(conservationStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
    }

    // PUT /api/conservation-statuses/{id} - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ConservationStatus> updateConservationStatus(
            @PathVariable Long id,
            @RequestBody ConservationStatus statusDetails) {
        return ResponseEntity.ok(conservationStatusService.updateConservationStatus(id, statusDetails));
    }

    // DELETE /api/conservation-statuses/{id} - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConservationStatus(@PathVariable Long id) {
        conservationStatusService.deleteConservationStatus(id);
        return ResponseEntity.noContent().build();
    }
}