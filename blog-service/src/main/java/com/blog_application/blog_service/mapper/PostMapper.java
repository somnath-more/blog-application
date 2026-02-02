package com.blog_application.blog_service.mapper;


import com.blog_application.blog_service.dto.PostDTO;
import com.blog_application.blog_service.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Convert Entity to DTO
    public PostDTO convertToDto(Post entity) {
        if (entity == null) return null;
        return modelMapper.map(entity, PostDTO.class);
    }

    // Convert DTO to Entity
    public Post convertToEntity(PostDTO dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Post.class);
    }
}
