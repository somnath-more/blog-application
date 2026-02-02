package com.blog_application.blog_service.service.impl;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.PostDTO;
import com.blog_application.blog_service.exception.CustomException;
import com.blog_application.blog_service.mapper.PostMapper;
import com.blog_application.blog_service.model.Category;
import com.blog_application.blog_service.model.Post;
import com.blog_application.blog_service.model.User;
import com.blog_application.blog_service.repository.CategoryRepository;
import com.blog_application.blog_service.repository.PostRepository;
import com.blog_application.blog_service.repository.UserRepository;
import com.blog_application.blog_service.service.CategoryService;
import com.blog_application.blog_service.service.PostService;
import com.blog_application.blog_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);


    @Override
    public PostDTO createPost(PostDTO postDto, Long userId, Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Not Found : {} " + userId, HttpStatus.NOT_FOUND));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CustomException("Category Not Found : {} " + categoryId, HttpStatus.NOT_FOUND));
        Post post = postMapper.convertToEntity(postDto);
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return postMapper.convertToDto(savedPost);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException("Post Not Found : {} " + postId, HttpStatus.NOT_FOUND));
        return postMapper.convertToDto(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Long postId) {
        logger.info("Fetching post with ID: {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            logger.error("Post not found in database for ID: {}", postId);
            return new CustomException(String.format("Post Not Found with ID: %d", postId), HttpStatus.NOT_FOUND);
        });

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post savedPost = postRepository.save(post);


        return postMapper.convertToDto(savedPost);
    }

    @Override
    public List<PostDTO> getPostByUser(Long userId) {
        logger.info("Fetching post for user ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found in database for ID: {}", userId);
                    return new CustomException(
                            String.format("User Not Found with ID: %d", userId),
                            HttpStatus.NOT_FOUND);
                });
        List<Post> posts = postRepository.findByUser(user)
                .orElseThrow(() -> new CustomException(
                        String.format("Post with user [%s] not found", user.getId()),
                        HttpStatus.NOT_FOUND
                ));

        return posts.stream().map(post -> postMapper.convertToDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> postMapper.convertToDto(post)).collect(Collectors.toList());
    }

    @Override
    public APIResponse deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException("Post Not Found : " + postId, HttpStatus.NOT_FOUND));
        postRepository.deleteById(post.getId());
        return new APIResponse(true, "Post Deleted Successfully : " + postId);
    }


}
