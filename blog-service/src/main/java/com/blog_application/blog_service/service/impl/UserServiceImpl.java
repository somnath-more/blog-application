package com.blog_application.blog_service.service.impl;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.RegisterDTO;
import com.blog_application.blog_service.exception.CustomException;
import com.blog_application.blog_service.mapper.UserMapper;
import com.blog_application.blog_service.model.User;
import com.blog_application.blog_service.repository.UserRepository;
import com.blog_application.blog_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
//    LOGGER
    @Autowired
    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public RegisterDTO createUser(RegisterDTO userDto) {
        User user=userMapper.convertToEntity(userDto);
       User savedUser= userRepository.save(user);
//        Long id = savedUser.getId();


        return userMapper.convertToDto(savedUser);
    }

    @Override
    public RegisterDTO getUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new CustomException("User Not Found : {} "+userId, HttpStatus.NOT_FOUND));

        return userMapper.convertToDto(user);
    }

    @Override
    public RegisterDTO updateUser(RegisterDTO userDto, Long userId) {
        logger.info("Fetching user with ID: {}", userId);
        User user=userRepository.findById(userId).orElseThrow(()->{
                logger.error("User not found in database for ID: {}", userId);
                return new CustomException(String.format("User Not Found with ID: %d", userId), HttpStatus.NOT_FOUND);});
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        return userMapper.convertToDto(user);
    }

    @Override
    public RegisterDTO getUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return userMapper.convertToDto(user);
    }

    @Override
    public List<RegisterDTO> getAllUsers() {
        List<User> users=userRepository.findAll();

        return users.stream().map(user -> userMapper.convertToDto(user)).collect(Collectors.toList());
    }

    @Override
    public APIResponse deleteUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new CustomException("User Not Found : "+userId, HttpStatus.NOT_FOUND));
        userRepository.deleteById(userId);
        return new APIResponse(true,"User Deleted Successfully. {} "+userId);
    }


}
