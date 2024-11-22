package com.kltn.product_service.component.product.dto.response;


import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        String id,
        Instant createdAt,
        Instant updatedAt,
        String name,
        String imageUrl,
        String description,
        BigDecimal cost,
        BigDecimal price,
        BigDecimal marketPrice,
        Long stockQuantity,
        BrandResponse brand
) {
    public record BrandResponse(
        String id,
        String name,
        String information
    ){}
}
