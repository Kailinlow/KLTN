package com.kltn.product_service.component.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kltn.product_service.component.product.dto.request.CreateProductRequest;
import com.kltn.product_service.component.product.dto.request.UpdateProductRequest;
import com.kltn.product_service.component.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody UpdateProductRequest request, String id) {
        return ResponseEntity.ok(productService.updateProduct(request, id));
    }

    @GetMapping("/short/{id}")
    public ResponseEntity<?> getShortInformation(@PathVariable String id) {
        return ResponseEntity.ok(productService.getShortInformation(id));
    }


    @PostMapping("/upload-json")
    public ResponseEntity<String> uploadJsonFile(@RequestBody MultipartFile file) {
        try {
            // Chuyển nội dung file JSON thành danh sách CreateProductRequest
            ObjectMapper objectMapper = new ObjectMapper();
            List<CreateProductRequest> products = objectMapper.readValue(
                    file.getInputStream(),
                    new TypeReference<List<CreateProductRequest>>() {}
            );

            // Lưu các sản phẩm vào database
            for (CreateProductRequest request : products) {
                productService.create(request);
            }

            return ResponseEntity.ok("Products created successfully.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Invalid JSON file: " + e.getMessage());
        }
    }
}
