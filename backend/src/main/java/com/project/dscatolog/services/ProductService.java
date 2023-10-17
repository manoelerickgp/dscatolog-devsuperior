package com.project.dscatolog.services;

import com.project.dscatolog.dto.ProductDTO;
import com.project.dscatolog.entities.Product;
import com.project.dscatolog.mapper.ProductMapper;
import com.project.dscatolog.repositories.ProductRepository;
import com.project.dscatolog.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAllProducts() {
        return ProductMapper.toProductListDTO(productRepository.findAll());
    }

    public ProductDTO findById(Long id) {
        return ProductMapper.toProductDTO(returnProduct(id));
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = ProductMapper.toProduct(productDTO);
        return new ProductDTO(productRepository.save(product));
    }

    public void delete(Long id) {
        productRepository.delete(returnProduct(id));
    }

    public ProductDTO update(Long id, ProductDTO productDTO){
        Product productSaved = returnProduct(id);
        ProductMapper.updateProductData(productSaved, productDTO);
        return new ProductDTO(productSaved);
    }

    private Product returnProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    }
}
