package com.wonderlands.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlands.api.entities.Multimedia;

public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
	
}
