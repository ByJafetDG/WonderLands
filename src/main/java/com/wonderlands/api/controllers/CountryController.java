package com.wonderlands.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wonderlands.api.entities.*;
import com.wonderlands.api.services.CountryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
	@Autowired
    private CountryService countryService;
    
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(countryService.findAll());
    }
    
    @GetMapping("/{code}/regions")
    public ResponseEntity<List<Region>> getRegionsByCountry(@PathVariable String code) {
        return ResponseEntity.ok(countryService.findRegionsByCountry(code));
    }
}
