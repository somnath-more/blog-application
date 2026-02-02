package com.blog_application.blog_service.service;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO userDto, Long userId, Long categoryId);
    PostDTO getPostById(Long userId);
    PostDTO updatePost(PostDTO userDto, Long userId);
    List<PostDTO> getPostByUser(Long userId);
    List<PostDTO> getAllPosts();
    APIResponse deletePostById(Long userId);

}
