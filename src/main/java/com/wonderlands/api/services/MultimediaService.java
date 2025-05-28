package com.wonderlands.api.services;

import com.wonderlands.api.entities.Multimedia;
import com.wonderlands.api.entities.Species;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.MultimediaRepository;
import com.wonderlands.api.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;
    
    @Autowired
    private SpeciesRepository speciesRepository;

    // GET - Obtener todos los recursos multimedia
    public List<Multimedia> findAll() {
        return multimediaRepository.findAll();
    }

    // GET - Obtener por ID
    public Multimedia findById(Long id) {
        return multimediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso multimedia no encontrado"));
    }

    // GET - Obtener multimedia por especie
    public List<Multimedia> findBySpeciesId(Long speciesId) {
        if (!speciesRepository.existsById(speciesId)) {
            throw new ResourceNotFoundException("Especie no encontrada");
        }
        return multimediaRepository.findBySpeciesId(speciesId);
    }

    // POST - Crear nuevo recurso multimedia
    public Multimedia createMultimedia(Multimedia multimedia) {
        // Validar que la especie existe
        if (multimedia.getSpecies() != null && multimedia.getSpecies().getId() != null) {
            Species species = speciesRepository.findById(multimedia.getSpecies().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada"));
            multimedia.setSpecies(species);
        }
        
        return multimediaRepository.save(multimedia);
    }

    // PUT - Actualizar recurso multimedia
    public Multimedia updateMultimedia(Long id, Multimedia multimediaDetails) {
        Multimedia multimedia = multimediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso multimedia no encontrado"));

        if (multimediaDetails.getUrl() != null) {
            multimedia.setUrl(multimediaDetails.getUrl());
        }
        if (multimediaDetails.getType() != null) {
            multimedia.setType(multimediaDetails.getType());
        }
        if (multimediaDetails.getDescription() != null) {
            multimedia.setDescription(multimediaDetails.getDescription());
        }
        // Actualizar especie si se proporciona
        if (multimediaDetails.getSpecies() != null && multimediaDetails.getSpecies().getId() != null) {
            Species species = speciesRepository.findById(multimediaDetails.getSpecies().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada"));
            multimedia.setSpecies(species);
        }

        return multimediaRepository.save(multimedia);
    }

    // DELETE - Eliminar recurso multimedia
    public void deleteMultimedia(Long id) {
        Multimedia multimedia = multimediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso multimedia no encontrado"));
        
        multimediaRepository.delete(multimedia);
    }
}