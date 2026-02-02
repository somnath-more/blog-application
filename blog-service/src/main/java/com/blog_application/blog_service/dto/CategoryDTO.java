package com.blog_application.blog_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Category title is required")
    @Size(min = 3, message = "Title must be at least 3 characters")
    private String title;

    private String description;
}
