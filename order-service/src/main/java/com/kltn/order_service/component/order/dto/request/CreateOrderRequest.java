package com.kltn.order_service.component.order.dto.request;

import com.kltn.order_service.component.orderItem.dto.request.CreateOrderItemRequest;
import com.kltn.order_service.component.orderItem.dto.request.OrderItemRequest;
import com.kltn.order_service.component.orderItem.dto.response.OrderItemResponse;
import lombok.*;

import java.util.List;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    private String userId;
    private String orderDate;
    private List<CreateOrderItemRequest> orderItemList;
}
