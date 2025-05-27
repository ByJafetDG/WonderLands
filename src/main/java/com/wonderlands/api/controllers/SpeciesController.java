package com.wonderlands.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wonderlands.api.entities.Species;
import com.wonderlands.api.services.SpeciesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpeciesController {
	@Autowired
    private SpeciesService speciesService;
    
    @GetMapping
    public ResponseEntity<List<Species>> getAllSpecies() {
        return ResponseEntity.ok(speciesService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Species> getSpeciesById(@PathVariable Long id) {
        return ResponseEntity.ok(speciesService.findById(id));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Species>> searchSpecies(
            @RequestParam(required = false) String commonName,
            @RequestParam(required = false) String scientificName) {
        if (commonName != null) {
            return ResponseEntity.ok(speciesService.searchByCommonName(commonName));
        } else if (scientificName != null) {
            return ResponseEntity.ok(speciesService.searchByScientificName(scientificName));
        }
        return ResponseEntity.badRequest().build();
    }
    
    @GetMapping("/country/{countryCode}")
    public ResponseEntity<List<Species>> getSpeciesByCountry(@PathVariable String countryCode) {
        return ResponseEntity.ok(speciesService.findByCountry(countryCode));
    }
}