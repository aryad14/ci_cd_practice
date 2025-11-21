package dev.aryadanech.Orders.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class HealthController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/health")
    public Object healthCheck() {
        try (Connection conn = dataSource.getConnection()) {
            return new Status("OK", "connected");
        } catch (Exception ex) {
            return new Status("ERROR", ex.getMessage());
        }
    }

    record Status(String status, String details) {}
}
