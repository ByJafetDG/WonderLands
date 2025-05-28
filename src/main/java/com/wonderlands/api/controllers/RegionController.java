package com.wonderlands.api.controllers;

import com.wonderlands.api.entities.Region;
import com.wonderlands.api.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    // GET /api/regions - Todas las regiones
    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        return ResponseEntity.ok(regionService.findAll());
    }

    // GET /api/regions/{id} - Región específica
    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        return ResponseEntity.ok(regionService.findById(id));
    }

    // GET /api/countries/{code}/regions - Regiones por país
    @GetMapping("/country/{code}")
    public ResponseEntity<List<Region>> getRegionsByCountry(@PathVariable String code) {
        return ResponseEntity.ok(regionService.findByCountryCode(code));
    }

    // POST /api/regions - Crear nueva región
    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(regionService.createRegion(region));
    }

    // PUT /api/regions/{id} - Actualizar región
    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(
            @PathVariable Long id,
            @RequestBody Region regionDetails) {
        return ResponseEntity.ok(regionService.updateRegion(id, regionDetails));
    }

    // DELETE /api/regions/{id} - Eliminar región
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}