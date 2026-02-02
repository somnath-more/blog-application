package com.blog_application.blog_service.repository;

import com.blog_application.blog_service.model.Comment;
import com.blog_application.blog_service.model.Post;
import com.blog_application.blog_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<List<Comment>> findByUser(User user);
    Optional<List<Comment>> findByPost(Post post);

}
