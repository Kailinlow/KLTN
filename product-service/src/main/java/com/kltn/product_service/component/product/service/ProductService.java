package com.kltn.product_service.component.product.service;

import com.kltn.product_service.component.brand.Brand;
import com.kltn.product_service.component.brand.BrandRepository;
import com.kltn.product_service.component.product.Product;
import com.kltn.product_service.component.product.ProductRepository;
import com.kltn.product_service.component.product.dto.request.CreateProductRequest;
import com.kltn.product_service.component.product.dto.request.UpdateProductRequest;
import com.kltn.product_service.component.product.dto.response.ProductResponse;
import com.kltn.product_service.component.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;

    public ProductResponse create(CreateProductRequest request) {
        Brand existingBrand = brandRepository.findById(request.brandId())
                .orElseThrow(() -> new RuntimeException("Brand is not existed"));

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
            throw new RuntimeException("Product isn't existed");
        }

        return productMapper.toProductResponse(productOptional.get());
    }

    public ProductResponse updateProduct(UpdateProductRequest request, String id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product is not existed"));

        productMapper.updateProductFromRequest(request, existingProduct);

        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toProductResponse(updatedProduct);
    }


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
