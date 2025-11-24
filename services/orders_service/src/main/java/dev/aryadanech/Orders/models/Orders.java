package dev.aryadanech.Orders.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Orders {
    @Id
    private Long orderId;
    private Long productId; // Reference to Product
    private Integer quantity;
    private Double totalPrice;

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + ", totalPrice="
                + totalPrice + "]";
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
