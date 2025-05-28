package com.wonderlands.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonderlands.api.entities.Country;
import com.wonderlands.api.entities.Region;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.CountryRepository;
import com.wonderlands.api.repositories.RegionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
    
	@Autowired
    private CountryRepository countryRepository;
	@Autowired
    private RegionRepository regionRepository;
    
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    
    public Country findByCode(String code) {
        return countryRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "code", code));
    }
    
    public List<Region> findRegionsByCountry(String countryCode) {
        // Primero verificamos que el país existe
        if (!countryRepository.existsById(countryCode)) {
            throw new ResourceNotFoundException("Country", "code", countryCode);
        }
        
        return regionRepository.findByCountryCode(countryCode);
    }
        
    
    public Country createCountry(Country country) {
        if(country.getCode() == null || country.getCode().isEmpty()) {
            throw new IllegalArgumentException("Country code is required");
        }
        if(countryRepository.existsById(country.getCode())) {
            throw new IllegalArgumentException("Country already exists");
        }
        return countryRepository.save(country);
    }
    
    public Country updateCountry(String code, Country countryDetails) {
        Country country = countryRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found"));
        
        if(countryDetails.getName() != null) {
            country.setName(countryDetails.getName());
        }
        
        return countryRepository.save(country);
    }
    
    public void deleteCountry(String code) {
        Country country = countryRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found"));
        
        if(!regionRepository.findByCountryCode(code).isEmpty()) {
            throw new IllegalStateException("Cannot delete country with associated regions");
        }
        
        countryRepository.delete(country);
    }
    
    
    
}