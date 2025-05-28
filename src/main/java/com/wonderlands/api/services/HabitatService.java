package com.wonderlands.api.services;

import com.wonderlands.api.entities.Habitat;
import com.wonderlands.api.exceptions.ResourceAlreadyExistsException;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.HabitatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitatService {

    @Autowired
    private HabitatRepository habitatRepository;

    // GET - Obtener todos los hábitats
    public List<Habitat> findAll() {
        return habitatRepository.findAll();
    }

    // GET - Obtener hábitat por ID
    public Habitat findById(Long id) {
        return habitatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hábitat no encontrado"));
    }

    // POST - Crear nuevo hábitat
    public Habitat createHabitat(Habitat habitat) {
        // Validar que no exista otro con el mismo nombre
        if (habitatRepository.existsByName(habitat.getName())) {
            throw new ResourceAlreadyExistsException("Ya existe un hábitat con ese nombre");
        }
        
        return habitatRepository.save(habitat);
    }

    // PUT - Actualizar hábitat
    public Habitat updateHabitat(Long id, Habitat habitatDetails) {
        Habitat habitat = habitatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hábitat no encontrado"));

        // Validar cambios en el nombre
        if (!habitat.getName().equals(habitatDetails.getName()) && 
            habitatRepository.existsByName(habitatDetails.getName())) {
            throw new ResourceAlreadyExistsException("Ya existe un hábitat con ese nombre");
        }

        habitat.setName(habitatDetails.getName());
        habitat.setDescription(habitatDetails.getDescription());

        return habitatRepository.save(habitat);
    }

    // DELETE - Eliminar hábitat
    public void deleteHabitat(Long id) {
        Habitat habitat = habitatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hábitat no encontrado"));
        
        habitatRepository.delete(habitat);
    }
}
