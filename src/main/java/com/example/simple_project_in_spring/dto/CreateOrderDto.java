package com.example.simple_project_in_spring.dto;

import com.example.simple_project_in_spring.model.OrderType;
import lombok.Data;

@Data
public class CreateOrderDto {
    //DTO - Data Transfer Object
    private String description;
    private OrderType type;

}
