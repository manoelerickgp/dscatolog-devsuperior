package com.project.dscatolog.mapper;

import com.project.dscatolog.dto.ProductDTO;
import com.project.dscatolog.entities.Product;

import java.util.List;

public class ProductMapper {

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
        return new ProductDTO(product);
    }

    public static List<ProductDTO> toProductListDTO(List<Product> productList) {
        return productList.stream().map(ProductDTO::new).toList();
    }

    public static void updateProductData(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        product.setDate(productDTO.getDate());
    }
}
