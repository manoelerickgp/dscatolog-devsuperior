package com.project.dscatolog.services;

import com.project.dscatolog.entities.Category;
import com.project.dscatolog.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAllCategories(){
         return repository.findAll();
    }

    public Category findCategoryById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
