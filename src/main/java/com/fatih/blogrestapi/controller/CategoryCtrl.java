package com.fatih.blogrestapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.blogrestapi.dto.CategoryDto;
import com.fatih.blogrestapi.service.CategoryService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/categories")
public class CategoryCtrl {

    private CategoryService categoryService;

    public CategoryCtrl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ! create category
    // ! ROLE ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto response = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(response);
    }

    // ! get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // ! get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // ! update category
    // ! ROLE ADMIN
    @PutMapping("{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {

        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    // ! delete category
    // ! ROLE ADMIN
    @DeleteMapping("{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }
}