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
}