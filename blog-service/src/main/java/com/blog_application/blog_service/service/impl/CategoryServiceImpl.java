package com.blog_application.blog_service.service.impl;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.CategoryDTO;
import com.blog_application.blog_service.exception.CustomException;
import com.blog_application.blog_service.mapper.CategoryMapper;
import com.blog_application.blog_service.model.Category;
import com.blog_application.blog_service.repository.CategoryRepository;
import com.blog_application.blog_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category savedCategory = categoryRepository.save(categoryMapper.convertToEntity(categoryDto));
        return categoryMapper.convertToDto(savedCategory);
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new CustomException("Category Not Found with ID: "+categoryId, HttpStatus.OK));
        return categoryMapper.convertToDto(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDto, Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new CustomException("Category Not Found with ID: "+categoryId, HttpStatus.OK));
        category.setId(category.getId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory=categoryRepository.save(category);
        return categoryMapper.convertToDto(updatedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
        return categories.stream().map(category ->categoryMapper.convertToDto(category)).toList();
    }

    @Override
    public APIResponse deleteCategoryById(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new CustomException("Category Not Found with ID: "+categoryId, HttpStatus.OK));
        APIResponse apiResponse=new APIResponse();
        categoryRepository.delete(category);
        apiResponse.setMessage("Category Deleted Successfully : "+categoryId);
        apiResponse.setSuccess(true);
        return apiResponse;
    }
}
