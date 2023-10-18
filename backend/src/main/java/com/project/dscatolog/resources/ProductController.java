package com.project.dscatolog.resources;

import com.project.dscatolog.dto.ProductDTO;
import com.project.dscatolog.entities.Product;
import com.project.dscatolog.mapper.ProductMapper;
import com.project.dscatolog.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<ProductDTO>> findAllProducts(){
        return ResponseEntity.ok().body(productService.findAllProducts());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

}
