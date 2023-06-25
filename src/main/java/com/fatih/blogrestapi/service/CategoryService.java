package com.fatih.blogrestapi.service;

import java.util.List;

import com.fatih.blogrestapi.dto.CategoryDto;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    String deleteCategoryById(Long id);
}
