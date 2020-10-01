package com.example.simple_project_in_spring.service;

import com.example.simple_project_in_spring.dto.UserDto;
import com.example.simple_project_in_spring.model.User;
import com.example.simple_project_in_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        repository.save(user);

    }

    @Override
    public User findUserByName(String name) {
        return repository.findByUsername(name);
    }
}
