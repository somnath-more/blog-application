package com.blog_application.blog_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Comment content cannot be empty")
    @Size(max = 500, message = "Comment must be under 500 characters")
    private String content;
    private PostDTO post;
    private RegisterDTO user;
}
