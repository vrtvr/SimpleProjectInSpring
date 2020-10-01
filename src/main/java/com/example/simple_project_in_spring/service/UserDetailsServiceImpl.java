package com.example.simple_project_in_spring.service;

import com.example.simple_project_in_spring.model.User;
import com.example.simple_project_in_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository repo;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repo.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User " + s + " doesn't exist in database");
        }
        return user;
    }
}