package com.blog_application.blog_service.service;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDto);
    CategoryDTO getCategoryById(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDto, Long categoryId);
    List<CategoryDTO> getAllCategories();
    APIResponse deleteCategoryById(Long categoryId);


}
