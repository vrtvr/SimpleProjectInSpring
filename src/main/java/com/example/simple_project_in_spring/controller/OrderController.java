package com.example.simple_project_in_spring.controller;

import com.example.simple_project_in_spring.dto.OrderDto;
import com.example.simple_project_in_spring.model.OrderCustomer;
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
    public ResponseEntity<Void> create(@RequestBody OrderDto orderDto) {
        service.saveOrder(orderDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteById(@RequestParam Integer id) {
        service.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderCustomer> edit(@PathVariable Integer orderId,
                                              @RequestBody OrderDto orderDto) {
        service.editOrderById(orderId, orderDto);
        return ResponseEntity.ok(service.findOrderById(orderId).get());
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<OrderCustomer>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<OrderCustomer> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(service.findOrderById(id)
                .orElse(null));
    }

}
