package com.kltn.product_service.component.product.mapper;

import com.kltn.product_service.component.product.Product;
import com.kltn.product_service.component.product.dto.request.CreateProductRequest;
import com.kltn.product_service.component.product.dto.response.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(CreateProductRequest request);
    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toResponses(List<Product> products);
}
