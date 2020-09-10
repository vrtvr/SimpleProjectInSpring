package com.example.simple_project_in_spring.controller;

import com.example.simple_project_in_spring.model.OrderCustomer;
import com.example.simple_project_in_spring.model.OrderType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<OrderCustomer> test() {
        OrderCustomer orderCustomer = OrderCustomer.builder()
                .description("opis testowy")
                .type(OrderType.TYPE_1)
                .build();

        return ResponseEntity.ok(orderCustomer);
    }
}
