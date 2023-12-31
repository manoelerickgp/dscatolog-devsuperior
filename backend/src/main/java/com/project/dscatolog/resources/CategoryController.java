package com.project.dscatolog.resources;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
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

    @PostMapping
    public ResponseEntity<CategoryDTO> insertCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryDTO = service.insert(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryDTO = service.update(id, categoryDTO);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
