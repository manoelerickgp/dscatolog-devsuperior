package com.project.dscatolog.mapper;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.dto.ProductDTO;
import com.project.dscatolog.entities.Category;
import com.project.dscatolog.entities.Product;
import com.project.dscatolog.repositories.CategoryRepository;

import java.util.List;

public class ProductMapper {

    private CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static Product toProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .imgUrl(productDTO.getImgUrl())
                .date(productDTO.getDate())
                .build();
    }

    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(product, product.getCategories());
    }

    public static List<ProductDTO> toProductListDTO(List<Product> productList) {
        return productList.stream().map(product -> new ProductDTO(product, product.getCategories())).toList();
    }

}
