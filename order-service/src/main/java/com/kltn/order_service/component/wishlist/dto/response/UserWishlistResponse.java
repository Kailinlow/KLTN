package com.kltn.order_service.component.wishlist.dto.response;

import com.kltn.order_service.dto.ProductDTO;
import com.kltn.order_service.dto.UserDTO;
import lombok.*;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWishlistResponse {
    private String id;

    private ProductDTO productDTO;
}
