package dev.aryadanech.Orders.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrdersEntityTest {

    @Test
    void testOrderEntityFields() {
        Orders order = new Orders();
        order.setOrderId(1L);
        order.setProductId(2L);
        order.setQuantity(5);
        order.setTotalPrice(100.0);

        assertEquals(1L, order.getOrderId());
        assertEquals(2L, order.getProductId());
        assertEquals(5, order.getQuantity());
        assertEquals(100.0, order.getTotalPrice());
    }
}