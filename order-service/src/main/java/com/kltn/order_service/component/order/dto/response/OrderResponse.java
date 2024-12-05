package com.kltn.order_service.component.order.dto.response;

import com.kltn.order_service.component.orderItem.dto.response.OrderItemResponse;
import com.kltn.order_service.dto.UserDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String id;

    private BigDecimal totalAmount;

    private LocalDateTime orderDate;

    private String status;

    private UserDTO user;

    private List<OrderItemResponse> orderItemList;
}
