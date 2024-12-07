package com.kltn.order_service.component.cart.dto.request;

import lombok.Builder;

@Builder
public record CreateCartRequest(
        String userId,
        String productId,
        Integer quantity
) {
}
