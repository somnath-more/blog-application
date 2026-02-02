package com.blog_application.blog_service.controller;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.CommentDTO;
import com.blog_application.blog_service.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable Long userId) {
        List<CommentDTO> commentDTOS= commentService.getCommentsByUserId(userId);
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }


    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO>  getCommentById(@PathVariable Long commentId) {
       CommentDTO commentDTO= commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable  Long postId) {
        List<CommentDTO> commentDTOS= commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long postId,@PathVariable Long userId,@Valid @RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment= commentService.createComment(postId, userId, commentDTO);
        return new ResponseEntity<>(savedComment , HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO>  updateComment(@Valid @RequestBody CommentDTO commentDto,@PathVariable Long commentId) {
       CommentDTO updateCommentDTO= commentService.updateComment(commentId, commentDto);
         return new ResponseEntity<>(updateCommentDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{commentId}")
    public APIResponse deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }
}

