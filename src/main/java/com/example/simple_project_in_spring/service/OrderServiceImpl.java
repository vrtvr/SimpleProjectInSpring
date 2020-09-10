package com.example.simple_project_in_spring.service;

import com.example.simple_project_in_spring.dto.OrderDto;
import com.example.simple_project_in_spring.model.OrderCustomer;
import com.example.simple_project_in_spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;


    @Override
    public void saveOrder(OrderDto orderDto) {
        OrderCustomer orderCustomer = OrderCustomer.builder()
                .description(orderDto.getDescription())
                .type(orderDto.getType())
                .build();

    repository.save(orderCustomer);
    }

    @Override
    public void deleteOrderById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void editOrderById(Integer id, OrderDto orderDto) {
        Optional<OrderCustomer> order = repository.findById(id);
        if (order.isEmpty())
            return;

        OrderCustomer o = order.get();
        if (orderDto.getDescription() != null && !orderDto.getDescription().isEmpty())
            o.setDescription(orderDto.getDescription());
        if (orderDto.getType() != null)
            o.setType(orderDto.getType());

        repository.save(o);

    }

    @Override
    public List<OrderCustomer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<OrderCustomer> findOrderById(Integer id) {
        return repository.findById(id);
    }
}
