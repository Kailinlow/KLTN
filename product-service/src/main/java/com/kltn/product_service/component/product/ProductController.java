package com.kltn.product_service.component.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kltn.product_service.component.image.ImageService;
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
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/short/{id}")
    public ResponseEntity<?> getShortInformation(@PathVariable String id) {
        return ResponseEntity.ok(productService.getShortInformation(id));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> findImages(@PathVariable String id) {
        return ResponseEntity.ok(imageService.findByProductId(id));
    }
}
