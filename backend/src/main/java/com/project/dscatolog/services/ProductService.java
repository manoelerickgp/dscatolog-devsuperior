package com.project.dscatolog.services;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.dto.ProductDTO;
import com.project.dscatolog.entities.Category;
import com.project.dscatolog.entities.Product;
import com.project.dscatolog.mapper.ProductMapper;
import com.project.dscatolog.repositories.CategoryRepository;
import com.project.dscatolog.repositories.ProductRepository;
import com.project.dscatolog.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> findAllProducts() {
        return ProductMapper.toProductListDTO(productRepository.findAll());
    }

    public ProductDTO findById(Long id) {
        return ProductMapper.toProductDTO(returnProduct(id));
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
        updateProductData(product, productDTO);
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public void delete(Long id) {
        productRepository.delete(returnProduct(id));
    }

    public ProductDTO update(Long id, ProductDTO productDTO){
        Product productSaved = returnProduct(id);
        updateProductData(productSaved, productDTO);
        return new ProductDTO(productRepository.save(productSaved), productSaved.getCategories());
    }

    private Product returnProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    }

    private void updateProductData(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        product.setDate(productDTO.getDate());
        product.getCategories().clear();

        for (CategoryDTO catDTO : productDTO.getCategories()){
            Category category = categoryRepository.getReferenceById(catDTO.getId());
            product.getCategories().add(category);
        }
    }
}
