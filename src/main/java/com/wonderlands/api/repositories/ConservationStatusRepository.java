package com.wonderlands.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlands.api.entities.ConservationStatus;

public interface ConservationStatusRepository extends JpaRepository<ConservationStatus, Long> {
	boolean existsByStatus(String status);
    boolean existsByAbbreviation(String abbreviation);
}