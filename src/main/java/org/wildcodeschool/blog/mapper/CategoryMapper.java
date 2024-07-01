package org.wildcodeschool.blog.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.blog.model.DTO.CategoryDTO;
import org.wildcodeschool.blog.model.entity.Category;

@Component
public class CategoryMapper {
    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}
