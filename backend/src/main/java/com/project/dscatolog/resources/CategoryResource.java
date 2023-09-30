package com.project.dscatolog.resources;

import com.project.dscatolog.entities.Category;
import com.project.dscatolog.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService service;

    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Category>> findAllCategories(){
        List<Category> list = service.findAllCategories();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/findCategory/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        Category entity = service.findCategoryById(id);
        return ResponseEntity.ok().body(entity);
    }
}
