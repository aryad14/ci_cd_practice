package dev.aryadanech.Orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.aryadanech.Orders.models.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
}
