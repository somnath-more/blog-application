package com.blog_application.blog_service.service;

import com.blog_application.blog_service.dto.APIResponse;
import com.blog_application.blog_service.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    //    GET Users Comments by userId
    List<CommentDTO> getCommentsByUserId(Long userId);
//    getCommentById
    CommentDTO getCommentById(Long commentId);
    //    GET Post Comments by postId
    List<CommentDTO> getCommentsByPostId(Long postId);

    //    CREATE Comment for a Post by postId and userId
    CommentDTO createComment(Long postId, Long userId, CommentDTO commentDTO);

    //    UPDATE Comment by commentId
    CommentDTO updateComment(Long commentId, CommentDTO commentDTO);

    // DELETE COMMENT by commentId
    APIResponse deleteComment(Long commentId);
}


