package com.blog_application.blog_service.controller;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.CategoryDTO;
import com.blog_application.blog_service.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto) {
          CategoryDTO  savedCategoryDto =categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategoryDto,HttpStatus.CREATED);
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO>  getCategoryById(@PathVariable Long categoryId) {
        CategoryDTO  categoryDto =categoryService.getCategoryById(categoryId);

        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO>  updateCategory(@Valid @RequestBody CategoryDTO categoryDto,@PathVariable Long categoryId) {
        CategoryDTO updatedCategoryDto = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>>  getAllCategories() {
        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDTOs,HttpStatus.OK);

    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<APIResponse> deleteCategoryById(@PathVariable Long categoryId) {
        APIResponse apiResponse = categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}

