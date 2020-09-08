package com.example.simple_project_in_spring.controller;

import com.example.simple_project_in_spring.dto.CreateOrderDto;
import com.example.simple_project_in_spring.model.Order;
import com.example.simple_project_in_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService service;

    //warstwa serwisu
    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> create(@RequestBody CreateOrderDto createOrderDto) {
        service.saveOrder(createOrderDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteById(@RequestParam Integer id) {
        service.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> edit(@PathVariable Integer orderId,
                                      @RequestBody CreateOrderDto createOrderDto) {
        service.editOrderById(orderId, createOrderDto);
        return ResponseEntity.ok(service.findOrderById(orderId).get());
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Order> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(service.findOrderById(id)
                .orElse(null));
    }

}
