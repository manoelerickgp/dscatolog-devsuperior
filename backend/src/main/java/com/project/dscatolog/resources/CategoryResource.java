package com.project.dscatolog.resources;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.entities.Category;
import com.project.dscatolog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService service;

    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CategoryDTO>> findAllCategories(){
        List<CategoryDTO> list = service.findAllCategories();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/findCategory/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id) {
        CategoryDTO entity = service.findCategoryById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<CategoryDTO> insertCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryDTO = service.insert(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryDTO = service.update(id, categoryDTO);
        return ResponseEntity.ok().body(categoryDTO);
    }
}
