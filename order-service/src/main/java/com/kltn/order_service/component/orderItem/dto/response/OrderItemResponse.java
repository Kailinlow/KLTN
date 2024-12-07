package com.kltn.order_service.component.orderItem.dto.response;

import com.kltn.order_service.dto.ProductDTO;
import lombok.*;

import java.math.BigDecimal;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {
    private String id;

    private ProductDTO product;

    private Integer quantity;

    private BigDecimal price;
}
