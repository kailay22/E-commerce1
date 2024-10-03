package com.example.ClientDemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.orderservice.ProductClient;
import com.example.orderservice.Order;
import com.example.productservice.Product;
import com.example.orderservice.OrderRepository;
import com.example.orderservice.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

class OrderServiceTest {

    @Mock
    private ProductClient productClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;
    private Product sampleProduct;
    private Order sampleOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleProduct = new Product(1, "Test Product", BigDecimal.valueOf(100.00), "Test description");
        sampleOrder = new Order(1, 1, 2, BigDecimal.valueOf(200.00));
    }

    @Test
    void testCreateOrder_Success() {
        when(productClient.getProductById(1)).thenReturn(sampleProduct);
        when(orderRepository.save(any(Order.class))).thenReturn(sampleOrder);

        Order result = orderService.createOrder(1, 2);

        assertNotNull(result);
        assertEquals(1, result.getProductId());
        assertEquals(2, result.getQuantity());
        assertEquals(BigDecimal.valueOf(200.00), result.getTotalPrice());

        verify(productClient, times(1)).getProductById(1);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testCreateOrder_ProductNotFound() {
        when(productClient.getProductById(1)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> orderService.createOrder(1, 2));
    }
}

