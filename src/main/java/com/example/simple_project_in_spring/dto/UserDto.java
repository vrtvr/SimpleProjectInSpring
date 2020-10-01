package com.example.simple_project_in_spring.dto;

import com.example.simple_project_in_spring.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private Role roles;
}
