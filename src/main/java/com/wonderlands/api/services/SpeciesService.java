package com.wonderlands.api.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonderlands.api.entities.Category;
import com.wonderlands.api.entities.ConservationStatus;
import com.wonderlands.api.entities.Habitat;
import com.wonderlands.api.entities.Multimedia;
import com.wonderlands.api.entities.Region;
import com.wonderlands.api.entities.Species;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.CategoryRepository;
import com.wonderlands.api.repositories.ConservationStatusRepository;
import com.wonderlands.api.repositories.HabitatRepository;
import com.wonderlands.api.repositories.MultimediaRepository;
import com.wonderlands.api.repositories.RegionRepository;
import com.wonderlands.api.repositories.SpeciesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpeciesService {
	
	@Autowired
    private SpeciesRepository speciesRepository;
	@Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ConservationStatusRepository conservationStatusRepository;
    @Autowired
    private HabitatRepository habitatRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private MultimediaRepository multimediaRepository;
    

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
    
    
 // POST - Crear nueva especie
    public Species createSpecies(Species species) {
        // Validaciones básicas
        if(species.getCommonName() == null || species.getCommonName().isEmpty()) {
            throw new IllegalArgumentException("El nombre común es requerido");
        }

        // Validar y cargar relaciones
        if(species.getCategory() != null && species.getCategory().getId() != null) {
            Category category = categoryRepository.findById(species.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
            species.setCategory(category);
        }

        if(species.getConservationStatus() != null && species.getConservationStatus().getId() != null) {
            ConservationStatus status = conservationStatusRepository.findById(species.getConservationStatus().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Estado de conservación no encontrado"));
            species.setConservationStatus(status);
        }

        // Guardar la especie primero para obtener ID
        Species savedSpecies = speciesRepository.save(species);

        // Manejar relaciones ManyToMany y colecciones
        if(species.getHabitats() != null) {
            Set<Habitat> managedHabitats = new HashSet<>();
            for(Habitat habitat : species.getHabitats()) {
                managedHabitats.add(habitatRepository.findById(habitat.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Hábitat no encontrado")));
            }
            savedSpecies.setHabitats(managedHabitats);
        }

        if(species.getRegions() != null) {
            Set<Region> managedRegions = new HashSet<>();
            for(Region region : species.getRegions()) {
                managedRegions.add(regionRepository.findById(region.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Región no encontrada")));
            }
            savedSpecies.setRegions(managedRegions);
        }

        // Manejar multimedia (si existe)
        if(species.getMultimedia() != null) {
            for(Multimedia media : species.getMultimedia()) {
                media.setSpecies(savedSpecies);
                multimediaRepository.save(media);
            }
        }

        return speciesRepository.save(savedSpecies);
    }

    // PUT - Actualizar especie completa
    public Species updateSpecies(Long id, Species speciesDetails) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada"));

        // Actualizar campos básicos
        if(speciesDetails.getCommonName() != null) {
            species.setCommonName(speciesDetails.getCommonName());
        }
        if(speciesDetails.getScientificName() != null) {
            species.setScientificName(speciesDetails.getScientificName());
        }
        if(speciesDetails.getType() != null) {
            species.setType(speciesDetails.getType());
        }
        if(speciesDetails.getDescriptions() != null) {
            species.setDescriptions(speciesDetails.getDescriptions());
        }

        // Actualizar relaciones
        if(speciesDetails.getCategory() != null) {
            Category category = categoryRepository.findById(speciesDetails.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
            species.setCategory(category);
        }

        if(speciesDetails.getConservationStatus() != null) {
            ConservationStatus status = conservationStatusRepository.findById(speciesDetails.getConservationStatus().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Estado de conservación no encontrado"));
            species.setConservationStatus(status);
        }

        // Actualizar relaciones ManyToMany
        if(speciesDetails.getHabitats() != null) {
            Set<Habitat> managedHabitats = new HashSet<>();
            for(Habitat habitat : speciesDetails.getHabitats()) {
                managedHabitats.add(habitatRepository.findById(habitat.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Hábitat no encontrado")));
            }
            species.setHabitats(managedHabitats);
        }

        if(speciesDetails.getRegions() != null) {
            Set<Region> managedRegions = new HashSet<>();
            for(Region region : speciesDetails.getRegions()) {
                managedRegions.add(regionRepository.findById(region.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Región no encontrada")));
            }
            species.setRegions(managedRegions);
        }

        return speciesRepository.save(species);
    }

    // DELETE - Eliminar especie
    public void deleteSpecies(Long id) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada"));
        
        speciesRepository.delete(species);
    }
    
    
    
    
}