package com.wonderlands.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonderlands.api.entities.Species;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.SpeciesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpeciesService {
	
	// en caso de error escribir Private Final
	@Autowired
    private SpeciesRepository speciesRepository;
    
    /*   //descomentar si da error por no tener inyectado el constructor
    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }
    */
    public List<Species> findAll() {
        return speciesRepository.findAll();
    }
    
    public Species findById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Species not found"));
    }
    
    public List<Species> searchByCommonName(String name) {
        return speciesRepository.findByCommonNameContainingIgnoreCase(name);
    }
    
    public List<Species> findByCountry(String countryCode) {
        return speciesRepository.findByRegionsCountryCode(countryCode);
    }
    
    public List<Species> searchByScientificName(String scientificName){
    	return speciesRepository.findByScientificNameContainingIgnoreCase(scientificName);
    }
    
    
}