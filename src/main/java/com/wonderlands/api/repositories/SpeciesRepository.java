package com.wonderlands.api.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlands.api.entities.Species;
import com.wonderlands.api.entities.SpeciesType;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    List<Species> findByCommonNameContainingIgnoreCase(String name);
    List<Species> findByScientificNameContainingIgnoreCase(String name);
    List<Species> findByType(SpeciesType type);
    List<Species> findByCategoryId(Long categoryId);
    List<Species> findByRegionsCountryCode(String countryCode);
}
