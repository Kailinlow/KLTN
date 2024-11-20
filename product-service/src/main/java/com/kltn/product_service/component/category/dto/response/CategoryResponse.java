package com.kltn.product_service.component.category.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        String id,
        String name,
        String description) {
}
