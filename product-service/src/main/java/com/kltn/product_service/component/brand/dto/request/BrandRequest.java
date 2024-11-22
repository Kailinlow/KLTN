package com.kltn.product_service.component.brand.dto.request;

import lombok.Builder;

@Builder
public record BrandRequest(
        String name,
        String information
) {
}
