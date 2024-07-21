package org.wildcodeschool.blog.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wildcodeschool.blog.mapper.CategoryMapper;
import org.wildcodeschool.blog.model.DTO.CategoryDTO;
import org.wildcodeschool.blog.model.entity.Category;
import org.wildcodeschool.blog.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::convertToDTO).toList();
    }

    public Optional<CategoryDTO> findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::convertToDTO);
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.convertToDTO(savedCategory);
    }

    public Optional<CategoryDTO> update(Long id, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return Optional.of(categoryMapper.convertToDTO(category.get()));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return true;
        } else {
            return false;
        }
    }
}
