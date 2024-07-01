package org.wildcodeschool.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.blog.model.Category;
import org.wildcodeschool.blog.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryController {

    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category categorySaved = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);
        if (categoryToUpdate == null) {
            return ResponseEntity.notFound().build();
        } else {
            categoryToUpdate.setName(category.getName());
            Category updateddCategory = categoryRepository.save(categoryToUpdate);
            return ResponseEntity.ok(updateddCategory);
        }
    }
}
