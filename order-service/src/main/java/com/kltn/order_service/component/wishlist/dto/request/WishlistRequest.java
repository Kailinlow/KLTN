package com.kltn.order_service.component.wishlist.dto.request;

import lombok.Builder;

@Builder
public record WishlistRequest(
        String userId,
        String productId
) {
}
