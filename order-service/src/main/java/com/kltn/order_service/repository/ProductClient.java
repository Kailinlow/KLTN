package com.kltn.order_service.repository;

import com.kltn.order_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductClient {

    @GetMapping("/products/short/{id}")
    ProductDTO getById(@PathVariable("id") String id);
}
