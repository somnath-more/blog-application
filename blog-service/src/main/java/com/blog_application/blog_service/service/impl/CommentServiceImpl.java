package com.blog_application.blog_service.service.impl;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.CommentDTO;
import com.blog_application.blog_service.exception.CustomException;
import com.blog_application.blog_service.mapper.CategoryMapper;
import com.blog_application.blog_service.mapper.CommentMapper;
import com.blog_application.blog_service.mapper.PostMapper;
import com.blog_application.blog_service.mapper.UserMapper;
import com.blog_application.blog_service.model.Category;
import com.blog_application.blog_service.model.Comment;
import com.blog_application.blog_service.model.Post;
import com.blog_application.blog_service.model.User;
import com.blog_application.blog_service.repository.CategoryRepository;
import com.blog_application.blog_service.repository.CommentRepository;
import com.blog_application.blog_service.repository.PostRepository;
import com.blog_application.blog_service.repository.UserRepository;
import com.blog_application.blog_service.service.CategoryService;
import com.blog_application.blog_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommentDTO> getCommentsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Not Found : {} " + userId, HttpStatus.NOT_FOUND));
        List<Comment> comments = commentRepository.findByUser(user).orElseThrow(()-> new CustomException("Comments Not Found for User ID : {} " + userId, HttpStatus.NOT_FOUND));
        return comments.stream().map(commentMapper::convertToDto).toList();
    }

    @Override
    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException("Comment Not Found : {} " + commentId, HttpStatus.NOT_FOUND));
        return commentMapper.convertToDto(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException("Post Not Found : {} " + postId, HttpStatus.NOT_FOUND));
        List<Comment> comments = commentRepository.findByPost(post).orElseThrow(()-> new CustomException("Comments Not Found for Post ID : {} " + postId, HttpStatus.NOT_FOUND));
        return comments.stream().map(commentMapper::convertToDto).toList();
    }

    @Override
    public CommentDTO createComment(Long postId, Long userId, CommentDTO commentDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User Not Found : {} " + userId, HttpStatus.NOT_FOUND));

        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException("Post Not Found : {} " + postId, HttpStatus.NOT_FOUND));
        Comment comment = commentMapper.convertToEntity(commentDTO);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.convertToDto(savedComment);
    }

    @Override
    public CommentDTO updateComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException("Comment Not Found : {} " + commentId, HttpStatus.NOT_FOUND));
        comment.setContent(commentDTO.getContent());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.convertToDto(updatedComment);
    }

    @Override
    public APIResponse deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException("Comment Not Found : {} " + commentId, HttpStatus.NOT_FOUND));
         commentRepository.delete(comment);

      return new APIResponse(true, "Comment deleted successfully : "+ commentId);
    }
}

