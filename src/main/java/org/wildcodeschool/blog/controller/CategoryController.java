package org.wildcodeschool.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.blog.mapper.CategoryMapper;
import org.wildcodeschool.blog.model.DTO.CategoryDTO;
import org.wildcodeschool.blog.model.entity.Category;
import org.wildcodeschool.blog.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryController {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CategoryDTO> categoriesDTO = categories.stream().map(categoryMapper::convertToDTO).toList();
        return ResponseEntity.ok(categoriesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryMapper.convertToDTO(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody Category category) {
        Category categorySaved = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.convertToDTO(categorySaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);
        if (categoryToUpdate == null) {
            return ResponseEntity.notFound().build();
        } else {
            categoryToUpdate.setName(category.getName());
            Category updateddCategory = categoryRepository.save(categoryToUpdate);
            return ResponseEntity.ok(categoryMapper.convertToDTO(updateddCategory));
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Category categoryToDelete = categoryRepository.findById(id).orElse(null);
        if (categoryToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
