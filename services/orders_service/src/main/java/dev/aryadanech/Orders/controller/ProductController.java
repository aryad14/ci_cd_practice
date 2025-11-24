package dev.aryadanech.Orders.controller;

import dev.aryadanech.Orders.models.Product;
import dev.aryadanech.Orders.services.ProductService;
import dev.aryadanech.Orders.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ApiResponse<Object> getProducts() {
        Object res = productService.getProducts();
        return ApiResponse.success(res, "Products fetched successfully");
    }

    @PostMapping
    public ApiResponse<Object> addProduct(@RequestBody Product product) {
        Object res = productService.saveProduct(product);
        return ApiResponse.success(res, "Product added successfully");
    }
}
