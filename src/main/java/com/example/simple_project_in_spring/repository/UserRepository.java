package com.example.simple_project_in_spring.repository;

import com.example.simple_project_in_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   User findByUsername(String username);
}
