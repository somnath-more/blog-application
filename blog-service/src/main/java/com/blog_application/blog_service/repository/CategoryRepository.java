package com.blog_application.blog_service.repository;

import com.blog_application.blog_service.model.Category;
import com.blog_application.blog_service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}

