package com.blog_application.blog_service.repository;

import com.blog_application.blog_service.dto.RegisterDTO;
import com.blog_application.blog_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
     User findByEmail(String email);
}
