package com.blog_application.blog_service.controller;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.RegisterDTO;
import com.blog_application.blog_service.service.UserService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@NoArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<RegisterDTO> createUser(@Valid @RequestBody RegisterDTO userDto){
         RegisterDTO registerDTO =userService.createUser(userDto);
        return new ResponseEntity<>(registerDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RegisterDTO> getUserById(@PathVariable Long userId) {
        RegisterDTO userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<RegisterDTO> updateUser(@Valid @RequestBody RegisterDTO userDto,@PathVariable Long userId) {
        RegisterDTO updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<RegisterDTO> getUserByEmail(@RequestParam(value = "email") String email) {
        RegisterDTO userDto = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<RegisterDTO>> getAllUsers() {
        List<RegisterDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<APIResponse> deleteUserById(@PathVariable Long userId) {
        APIResponse apiResponse= userService.deleteUserById(userId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
