package com.kltn.product_service.component.product.service;

import com.kltn.product_service.component.product.Product;
import com.kltn.product_service.component.product.ProductRepository;
import com.kltn.product_service.component.product.dto.request.CreateProductRequest;
import com.kltn.product_service.component.product.dto.response.ProductResponse;
import com.kltn.product_service.component.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse create(CreateProductRequest request) {
        Product product = productMapper.toProduct(request);

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> findAll() {
        List<Product> productList = productRepository.findAll();

        return productMapper.toResponses(productList);
    }

    public ProductResponse findById(String id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product is not existed");
        }

        return productMapper.toProductResponse(productOptional.get());
    }


}
