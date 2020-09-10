package com.example.simple_project_in_spring.service;

import com.example.simple_project_in_spring.dto.OrderDto;
import com.example.simple_project_in_spring.model.OrderCustomer;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void saveOrder (OrderDto orderDto);

    void deleteOrderById (Integer id);

    void editOrderById (Integer id, OrderDto orderDto);

    List<OrderCustomer> findAll ();

    Optional<OrderCustomer>findOrderById (Integer id);
}
