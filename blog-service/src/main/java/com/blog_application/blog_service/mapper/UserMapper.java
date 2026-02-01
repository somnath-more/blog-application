package com.blog_application.blog_service.mapper;


import com.blog_application.blog_service.dto.RegisterDTO;
import com.blog_application.blog_service.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Convert Entity to DTO
    public RegisterDTO convertToDto(User entity) {
        if (entity == null) return null;
        return modelMapper.map(entity, RegisterDTO.class);
    }

    // Convert DTO to Entity
    public User convertToEntity(RegisterDTO dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, User.class);
    }
}
