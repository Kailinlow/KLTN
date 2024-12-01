package com.kltn.product_service.component.productAttributes.dto.request;

public record CreateProductAttributesRequest(
        String attributeName,
        String attributeValue,
        Integer oderPoint,
        String productId
) {
}
