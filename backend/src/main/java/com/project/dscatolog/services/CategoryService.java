package com.project.dscatolog.services;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.entities.Category;
import com.project.dscatolog.mapper.CategoryMapper;
import com.project.dscatolog.repositories.CategoryRepository;
import com.project.dscatolog.services.exceptions.DatabaseIntegrityException;
import com.project.dscatolog.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> findAllCategories(){
        return CategoryMapper.toCategoryDtoList(repository.findAll());
    }

    public CategoryDTO findCategoryById(Long id) {
        Category category = returnCategory(id);
        return CategoryMapper.toCategoryDTO(category);
    }

    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toCategory(categoryDTO);
        return CategoryMapper.toCategoryDTO(repository.save(category));
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
            Category category = returnCategory(id);
            category.setName(categoryDTO.getName());
            //CategoryMapper.updateCategoryData(category, categoryDTO);
            return CategoryMapper.toCategoryDTO(repository.save(category));
    }

    public void delete(Long id) {
        try {
            repository.delete(returnCategory(id));
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Integrity Violation");
        }
    }

    private Category returnCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
    }
}
