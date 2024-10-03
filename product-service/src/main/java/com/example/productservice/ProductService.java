package com.example.productservice;

import com.example.productservice.Product;
import com.example.productservice.Product;
import com.example.productservice.ProductRepository;
import com.example.productservice.exception.ProductNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.productservice.config.RedisConfig;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }
}
