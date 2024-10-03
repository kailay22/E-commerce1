package com.example.orderservice;

import com.example.productservice.ProductDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(name = "product-service", url = "http://localhost:8080")  // Update URL if needed
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDetails getProductById(@PathVariable("id") Integer id);

}
