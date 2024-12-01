package com.kltn.product_service.component.product.mapper;

import com.kltn.product_service.component.product.Product;
import com.kltn.product_service.component.product.dto.request.CreateProductRequest;
import com.kltn.product_service.component.product.dto.request.UpdateProductRequest;
import com.kltn.product_service.component.product.dto.response.ProductInfoResponse;
import com.kltn.product_service.component.product.dto.response.ProductResponse;
import com.kltn.product_service.component.productAttributes.dto.response.ProductAttributesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(CreateProductRequest request);
    ProductResponse toProductResponse(Product product);
    ProductInfoResponse toProductInfoResponse(Product product);
    List<ProductResponse> toResponses(List<Product> products);

    void updateProductFromRequest(UpdateProductRequest request, @MappingTarget Product product);

}
