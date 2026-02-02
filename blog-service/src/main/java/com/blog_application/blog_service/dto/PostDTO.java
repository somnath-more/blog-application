package com.blog_application.blog_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 4, message = "Title must be at least 4 characters")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;
    private String publishedDate;

    // Optional field: no validation annotation needed
    private String imageName;

    // This helps ModelMapper or manual mapping link the user
    private RegisterDTO user;
    private List<CommentDTO> comments;
    private CategoryDTO category;

}
