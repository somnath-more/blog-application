package com.blog_application.blog_service.mapper;

import com.blog_application.blog_service.dto.CategoryDTO;
import com.blog_application.blog_service.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category convertToEntity(CategoryDTO categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
