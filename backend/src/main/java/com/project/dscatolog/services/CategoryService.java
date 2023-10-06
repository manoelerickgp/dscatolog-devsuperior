package com.project.dscatolog.services;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.entities.Category;
import com.project.dscatolog.repositories.CategoryRepository;
import com.project.dscatolog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category category = new Category();
        copyDtoToEntity(category, categoryDTO);
        category = repository.save(category);
        return new CategoryDTO(category);
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        try {
            Category category = repository.getReferenceById(id);
            copyDtoToEntity(category, categoryDTO);
            category = repository.save(category);
            return new CategoryDTO(category);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Category Id Not Found");
        }
    }

    private void copyDtoToEntity(Category category, CategoryDTO categoryDTO){
        category.setName(categoryDTO.getName());
    }
}
