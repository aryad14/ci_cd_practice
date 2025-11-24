package dev.aryadanech.Orders.services;

import dev.aryadanech.Orders.models.Product;
import dev.aryadanech.Orders.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void testGetProductsReturnsList() {
        ProductRepository repo = mock(ProductRepository.class);
        ProductService service = new ProductService();
        service.productRepository = repo;

        when(repo.findAll()).thenReturn(Collections.emptyList());

        List<Product> products = service.getProducts();
        assertNotNull(products);
        assertEquals(0, products.size());
    }
}