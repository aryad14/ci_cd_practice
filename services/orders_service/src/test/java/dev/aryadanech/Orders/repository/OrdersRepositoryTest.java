package dev.aryadanech.Orders.repository;

import dev.aryadanech.Orders.models.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void testSaveAndFindOrder() {
        Orders order = new Orders();
        order.setOrderId(1L);
        order.setProductId(2L);
        order.setQuantity(3);
        order.setTotalPrice(50.0);

        ordersRepository.save(order);

        Orders found = ordersRepository.findById(1L).orElse(null);
        assertNotNull(found);
        assertEquals(2L, found.getProductId());
        assertEquals(3, found.getQuantity());
        assertEquals(50.0, found.getTotalPrice());
    }
}