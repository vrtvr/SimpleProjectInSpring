package com.example.simple_project_in_spring.service;

import com.example.simple_project_in_spring.dto.CreateOrderDto;
import com.example.simple_project_in_spring.model.Order;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    void saveOrder (CreateOrderDto createOrderDto);

    void deleteOrderById (Integer id);

    void editOrderById (Integer id, CreateOrderDto createOrderDto);

    List<Order> findAll ();

    Optional<Order>findOrderById (Integer id);
}
