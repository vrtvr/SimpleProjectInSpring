package com.example.simple_project_in_spring.repository;

import com.example.simple_project_in_spring.model.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderCustomer, Integer> {
}
