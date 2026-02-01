package com.blog_application.blog_service.service;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.RegisterDTO;

import java.util.List;

public interface UserService {
    RegisterDTO createUser(RegisterDTO userDto);
    RegisterDTO getUserById(Long userId);
    RegisterDTO updateUser(RegisterDTO userDto, Long userId);
    RegisterDTO getUserByEmail(String email);
    List<RegisterDTO> getAllUsers();
    APIResponse deleteUserById(Long userId);

}
