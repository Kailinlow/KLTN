package com.kltn.product_service.component.product.dto.request;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        String sku,
        String imageUrl,
        String description,
        BigDecimal cost,
        BigDecimal price,
        BigDecimal marketPrice,
        Long stockQuantity,
        String brandId) {
}
