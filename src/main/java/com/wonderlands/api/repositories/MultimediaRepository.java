package com.wonderlands.api.repositories;

import com.wonderlands.api.entities.Multimedia;
import com.wonderlands.api.entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
    List<Multimedia> findBySpeciesId(Long speciesId);
}