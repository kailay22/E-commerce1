package com.example.productservice;

import java.math.BigDecimal;
import java.util.Objects;

public final class ProductDetails {

    private final Integer id;        // Final field for immutability
    private final String name;       // Final field for immutability
    private final BigDecimal price;  // BigDecimal is immutable
    private final String description;

    // Constructor to initialize all fields
    public ProductDetails(Integer id, String name, BigDecimal price, String description) {
        if (id == null || name == null || price == null) {
            throw new IllegalArgumentException("ID, Name, and Price cannot be null");
        }

        if (name.length() < 3 || name.length() > 50) {
            throw new IllegalArgumentException("Product name must be between 3 and 50 characters");
        }

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be a positive value");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description != null ? description : "";  // Optional description, default to empty string
    }

    // Getter methods without setters (immutable)
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // Override equals and hashCode to ensure consistent behavior for equality checks
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetails that = (ProductDetails) o;
        return id.equals(that.id) && name.equals(that.name) && price.equals(that.price) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description);
    }

    // Override toString for better readability
    @Override
    public String toString() {
        return "ProductDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}

