package com.wonderlands.api.services;

import com.wonderlands.api.entities.Category;
import com.wonderlands.api.exceptions.ResourceAlreadyExistsException;
import com.wonderlands.api.exceptions.ResourceNotFoundException;
import com.wonderlands.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // GET - Obtener todas las categorías
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // GET - Obtener categoría por ID
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
    }

    // POST - Crear nueva categoría
    public Category createCategory(Category category) {
        // Validar que el nombre no exista
        if (categoryRepository.existsByName(category.getName())) {
            throw new ResourceAlreadyExistsException("Ya existe una categoría con ese nombre");
        }
        return categoryRepository.save(category);
    }

    // PUT - Actualizar categoría completa
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        // Validar que el nuevo nombre no exista (si se está cambiando)
        if (!category.getName().equals(categoryDetails.getName()) && 
            categoryRepository.existsByName(categoryDetails.getName())) {
            throw new ResourceAlreadyExistsException("Ya existe una categoría con ese nombre");
        }

        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());

        return categoryRepository.save(category);
    }

    // DELETE - Eliminar categoría
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        
        categoryRepository.delete(category);
    }
}