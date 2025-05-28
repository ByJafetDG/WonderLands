package com.wonderlands.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlands.api.entities.Habitat;

public interface HabitatRepository extends JpaRepository<Habitat, Long> {
	boolean existsByName(String name);
}