package com.wonderlands.api.services;

import com.wonderlands.api.entities.ConservationStatus;
import com.wonderlands.api.exceptions.ResourceAlreadyExistsException;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.ConservationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConservationStatusService {

    @Autowired
    private ConservationStatusRepository conservationStatusRepository;

    // GET - Obtener todos los estados de conservación
    public List<ConservationStatus> findAll() {
        return conservationStatusRepository.findAll();
    }

    // GET - Obtener por ID
    public ConservationStatus findById(Long id) {
        return conservationStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de conservación no encontrado"));
    }

    // POST - Crear nuevo estado
    public ConservationStatus createConservationStatus(ConservationStatus conservationStatus) {
        // Validar que no exista otro con el mismo status o abreviatura
        if (conservationStatusRepository.existsByStatus(conservationStatus.getStatus())) {
            throw new ResourceAlreadyExistsException("Ya existe un estado con ese nombre");
        }
        if (conservationStatusRepository.existsByAbbreviation(conservationStatus.getAbbreviation())) {
            throw new ResourceAlreadyExistsException("Ya existe un estado con esa abreviatura");
        }
        
        return conservationStatusRepository.save(conservationStatus);
    }

    // PUT - Actualizar estado
    public ConservationStatus updateConservationStatus(Long id, ConservationStatus statusDetails) {
        ConservationStatus status = conservationStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de conservación no encontrado"));

        // Validar cambios en status
        if (!status.getStatus().equals(statusDetails.getStatus()) && 
            conservationStatusRepository.existsByStatus(statusDetails.getStatus())) {
            throw new ResourceAlreadyExistsException("Ya existe un estado con ese nombre");
        }

        // Validar cambios en abbreviation
        if (!status.getAbbreviation().equals(statusDetails.getAbbreviation()) && 
            conservationStatusRepository.existsByAbbreviation(statusDetails.getAbbreviation())) {
            throw new ResourceAlreadyExistsException("Ya existe un estado con esa abreviatura");
        }

        status.setStatus(statusDetails.getStatus());
        status.setAbbreviation(statusDetails.getAbbreviation());

        return conservationStatusRepository.save(status);
    }

    // DELETE - Eliminar estado
    public void deleteConservationStatus(Long id) {
        ConservationStatus status = conservationStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado de conservación no encontrado"));
        
        conservationStatusRepository.delete(status);
    }
}