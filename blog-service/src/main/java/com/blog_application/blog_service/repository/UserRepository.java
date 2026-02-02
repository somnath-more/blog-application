package com.blog_application.blog_service.repository;

import com.blog_application.blog_service.dto.RegisterDTO;
import com.blog_application.blog_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findByEmail(String email);
}
