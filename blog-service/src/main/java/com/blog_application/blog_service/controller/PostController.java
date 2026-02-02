package com.blog_application.blog_service.controller;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.PostDTO;
import com.blog_application.blog_service.service.PostService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@NoArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long userId, @PathVariable Long categoryId){
         PostDTO registerDTO =postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(registerDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO,@PathVariable Long postId) {
        PostDTO updatedPost = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Long userId) {
        List<PostDTO> postDTO = postService.getPostByUser(userId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<APIResponse> deletePostById(@PathVariable Long postId) {
        APIResponse apiResponse= postService.deletePostById(postId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
