package com.kltn.product_service.component.brand.dto.response;

import lombok.Builder;

@Builder
public record BrandResponse(
        String id,
        String name,
        String information
) {
}
