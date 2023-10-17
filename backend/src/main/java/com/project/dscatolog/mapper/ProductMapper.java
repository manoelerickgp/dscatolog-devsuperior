package com.project.dscatolog.mapper;

import com.project.dscatolog.dto.ProductDTO;
import com.project.dscatolog.entities.Product;

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
}
