package com.wonderlands.api.services;

import com.wonderlands.api.entities.Region;
import com.wonderlands.api.entities.Country;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.RegionRepository;
import com.wonderlands.api.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    private CountryRepository countryRepository;

    // GET - Obtener todas las regiones
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    // GET - Obtener región por ID
    public Region findById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region no encontrada"));
    }

    // GET - Obtener regiones por país
    public List<Region> findByCountryCode(String countryCode) {
        if (!countryRepository.existsById(countryCode)) {
            throw new ResourceNotFoundException("País no encontrado");
        }
        return regionRepository.findByCountryCode(countryCode);
    }

    // POST - Crear nueva región
    public Region createRegion(Region region) {
        // Validar que el país existe
        Country country = countryRepository.findById(region.getCountry().getCode())
                .orElseThrow(() -> new ResourceNotFoundException("País no encontrado"));
        
        region.setCountry(country);
        return regionRepository.save(region);
    }

    // PUT - Actualizar región completa
    public Region updateRegion(Long id, Region regionDetails) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region no encontrada"));

        // Validar que el país existe si se está actualizando
        if (regionDetails.getCountry() != null) {
            Country country = countryRepository.findById(regionDetails.getCountry().getCode())
                    .orElseThrow(() -> new ResourceNotFoundException("País no encontrado"));
            region.setCountry(country);
        }

        if (regionDetails.getName() != null) {
            region.setName(regionDetails.getName());
        }

        return regionRepository.save(region);
    }

    // DELETE - Eliminar región
    public void deleteRegion(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region no encontrada"));
        
        regionRepository.delete(region);
    }
}
