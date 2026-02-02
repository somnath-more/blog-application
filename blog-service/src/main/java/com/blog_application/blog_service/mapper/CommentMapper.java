package com.blog_application.blog_service.mapper;


import com.blog_application.blog_service.dto.CommentDTO;
import com.blog_application.blog_service.dto.RegisterDTO;
import com.blog_application.blog_service.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Convert Entity to DTO
    public CommentDTO convertToDto(Comment entity) {
        if (entity == null) return null;
        return modelMapper.map(entity, CommentDTO.class);
    }

    // Convert DTO to Entity
    public Comment convertToEntity(CommentDTO dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Comment.class);
    }
}
