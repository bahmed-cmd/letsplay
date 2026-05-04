package com.letsplay.controller;

import com.letsplay.dto.ProductRequest;
import com.letsplay.model.Product;
import com.letsplay.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request,
                                                  Authentication authentication) {
        return ResponseEntity.ok(productService.createProduct(request, authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id,
                                                  @RequestBody ProductRequest request,
                                                  Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        return ResponseEntity.ok(productService.updateProduct(id, request, authentication.getName(), role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id,
                                               Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        productService.deleteProduct(id, authentication.getName(), role);
        return ResponseEntity.noContent().build();
    }
}