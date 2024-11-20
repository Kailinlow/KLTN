package com.kltn.product_service.component.product.Service;

import com.kltn.product_service.component.product.Product;
import com.kltn.product_service.component.product.ProductRepository;
import com.kltn.product_service.component.product.dto.request.CreateProductRequest;
import com.kltn.product_service.component.product.dto.response.ProductResponse;
import com.kltn.product_service.component.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
