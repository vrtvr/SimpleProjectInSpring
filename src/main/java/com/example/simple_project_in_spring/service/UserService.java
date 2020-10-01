package com.example.simple_project_in_spring.service;

import com.example.simple_project_in_spring.dto.UserDto;
import com.example.simple_project_in_spring.model.User;

public interface UserService {
    void createUser(UserDto userDto);

    User findUserByName(String name);
}
