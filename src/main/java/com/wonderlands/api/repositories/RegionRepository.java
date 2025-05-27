package com.wonderlands.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlands.api.entities.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByCountryCode(String countryCode);
}