package com.project.dscatolog.services;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.entities.Category;
import com.project.dscatolog.repositories.CategoryRepository;
import com.project.dscatolog.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> findAllCategories(){
        List<Category> list = repository.findAll();
        return list.stream().map(category -> new CategoryDTO(category)).toList();
    }

    public CategoryDTO findCategoryById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        return new CategoryDTO(category);
    }
}
