package com.example.orderservice;

import com.example.orderservice.Order;
import com.example.orderservice.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }


    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Integer productId, @RequestParam Integer quantity) {
        Order order = orderService.createOrder(productId, quantity);
        return ResponseEntity.ok(order);
    }
}
