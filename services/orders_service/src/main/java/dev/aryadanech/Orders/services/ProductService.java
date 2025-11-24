package dev.aryadanech.Orders.services;

import dev.aryadanech.Orders.models.Product;
import dev.aryadanech.Orders.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }
}
