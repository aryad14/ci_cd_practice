package dev.aryadanech.Orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.aryadanech.Orders.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
