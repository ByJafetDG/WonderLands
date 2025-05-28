package com.wonderlands.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wonderlands.api.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	// Puedes agregar métodos personalizados aquí si los necesitas
		    boolean existsByName(String name);
}