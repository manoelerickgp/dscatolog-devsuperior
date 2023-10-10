package com.project.dscatolog.mapper;

import com.project.dscatolog.dto.CategoryDTO;
import com.project.dscatolog.entities.Category;

import java.util.List;

public class CategoryMapper {

    public static Category toCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .build();
    }

    public static CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(category);
    }

    public static List<CategoryDTO> toCategoryDtoList(List<Category> categoryList) {
        return categoryList.stream().map(CategoryDTO::new).toList();
    }

    public static void updateCategoryData(Category category ,CategoryDTO categoryDTO) {
        category.setName(categoryDTO.getName());
    }
}
