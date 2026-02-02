package com.blog_application.blog_service.repository;

import com.blog_application.blog_service.model.Category;
import com.blog_application.blog_service.model.Post;
import com.blog_application.blog_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
//    findByUser
Optional<List<Post>> findByUser(User user);

}
