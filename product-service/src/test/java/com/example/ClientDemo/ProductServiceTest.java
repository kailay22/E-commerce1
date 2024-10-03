package com.example.ClientDemo;



import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;
        import com.example.productservice.Product;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.ProductRepository;
import com.example.productservice.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleProduct = new Product(1, "Test Product", BigDecimal.valueOf(100.00), "Test description");
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        Product result = productService.createProduct(sampleProduct);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(productRepository, times(1)).save(sampleProduct);
    }

    @Test
    void testGetProductById_Success() {
        when(productRepository.findById(1)).thenReturn(Optional.of(sampleProduct));

        Product result = productService.getProductById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Product", result.getName());
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1));
    }
}
