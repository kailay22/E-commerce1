package com.example.orderservice;

import com.example.orderservice.exception.OrderNotFoundException;
import com.example.productservice.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
    }


    public Order createOrder(Integer productId, Integer quantity) {
        // Fetch product details from ProductService via Feign Client
        ProductDetails product = productClient.getProductById(productId);

        // Calculate total price based on fetched product details
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        // Save the order
        return orderRepository.save(order);
    }


}
