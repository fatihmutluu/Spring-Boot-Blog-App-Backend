package com.fatih.blogrestapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fatih.blogrestapi.dto.CategoryDto;
import com.fatih.blogrestapi.exception.ResourceNotFoundException;
import com.fatih.blogrestapi.model.Category;
import com.fatih.blogrestapi.repository.CategoryRepo;
import com.fatih.blogrestapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper mapper) {
        this.categoryRepository = categoryRepo;
        this.mapper = mapper;
    }

    // ? create category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = mapToEntity(categoryDto);

        // category entity to DB
        Category newCategory = categoryRepository.save(category);

        return mapToDTO(newCategory);
    }

    // ? get all categories
    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> mapToDTO(category))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    // ? get category by id
    @Override
    public CategoryDto getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDTO(category);
    }

    // ? update category
    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        if (categoryDto.getName() != null)
            category.setName(categoryDto.getName());
        if (categoryDto.getDescription() != null)
            category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return mapToDTO(updatedCategory);

    }

    // ? delete category
    @Override
    public String deleteCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(category);

        return "Category deleted successfully";
    }

    // *mappers
    private CategoryDto mapToDTO(Category category) {
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        return category;
    }

}
