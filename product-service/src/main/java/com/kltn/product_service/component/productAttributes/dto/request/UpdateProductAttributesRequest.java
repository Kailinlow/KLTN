package com.kltn.product_service.component.productAttributes.dto.request;

public record UpdateProductAttributesRequest(
        String attributeName,
        String attributeValue,
        Integer oderPoint
) {
}
