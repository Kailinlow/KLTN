package com.kltn.order_service.component.cart.dto.response;

import com.kltn.order_service.dto.ProductDTO;
import com.kltn.order_service.dto.UserDTO;
import lombok.*;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCartResponse {
    private String id;

    private ProductDTO product;

    private Integer quantity;
}
