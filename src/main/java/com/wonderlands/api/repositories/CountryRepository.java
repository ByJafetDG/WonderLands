package com.wonderlands.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlands.api.entities.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
	
}