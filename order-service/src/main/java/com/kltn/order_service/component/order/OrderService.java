package com.kltn.order_service.component.order;

import com.kltn.order_service.component.order.dto.request.CreateOrderRequest;
import com.kltn.order_service.component.order.dto.request.OrderRequest;
import com.kltn.order_service.component.order.dto.response.OrderResponse;
import com.kltn.order_service.component.order.mapper.OrderMapper;
import com.kltn.order_service.component.orderItem.OrderItem;
import com.kltn.order_service.component.orderItem.OrderItemRepository;
import com.kltn.order_service.component.orderItem.dto.request.CreateOrderItemRequest;
import com.kltn.order_service.component.orderItem.dto.request.OrderItemRequest;
import com.kltn.order_service.component.orderItem.dto.response.OrderItemResponse;
import com.kltn.order_service.component.orderItem.mapper.OrderItemMapper;
import com.kltn.order_service.dto.ProductDTO;
import com.kltn.order_service.dto.UserDTO;
import com.kltn.order_service.repository.ProductClient;
import com.kltn.order_service.repository.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    private final UserClient userClient;
    private final ProductClient productClient;

    public OrderResponse create(OrderRequest request) {
        UserDTO userDTO = userClient.getUserById(request.getUserId());

        Order order = mapper.toOrder(request);
        order.setStatus("processing");

        repository.save(order);

        OrderResponse orderResponse = mapper.toOrderResponse(order);
        orderResponse.setUser(userDTO);

        return orderResponse;
    }

    public OrderResponse createNewOrder(CreateOrderRequest request) {
        OrderRequest orderRequest = new OrderRequest(request.getUserId(), request.getOrderDate());
        Order order = mapper.toOrder(orderRequest);
        order.setStatus("processing");

        Order newOrder = repository.save(order);

        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(BigDecimal.ZERO);

        OrderResponse orderResponse = mapper.toOrderResponse(newOrder);
        List<OrderItemResponse> orderItemResponsesList = new ArrayList<>();

        request.getOrderItemList().stream()
                .forEach(orderItemRequest -> {
                    OrderItemResponse orderItemResponse = createOrderItem(orderItemRequest, newOrder.getId(), newOrder);
                    orderItemResponsesList.add(orderItemResponse);
                    totalAmount.set(totalAmount.get().add(
                            orderItemResponse.getPrice().multiply(BigDecimal.valueOf(orderItemResponse.getQuantity()))
                    ));
                });

        try {
            UserDTO userDTO = userClient.getUserById(request.getUserId());
            orderResponse.setUser(userDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user details", e);
        }
        orderResponse.setOrderItemList(orderItemResponsesList);
        orderResponse.setTotalAmount(totalAmount.get());

        return orderResponse;
    }

    private OrderItemResponse createOrderItem(CreateOrderItemRequest request, String orderId, Order order){
        OrderItemRequest orderItemRequest = new OrderItemRequest(request.getQuantity(), orderId, request.getProductId());

        OrderItem newOrderItem = orderItemMapper.toOrderItem(orderItemRequest);
        newOrderItem.setOrder(order);

        ProductDTO productDTO = productClient.getById(request.getProductId());

        newOrderItem.setPrice(productDTO.getPrice());
        orderItemRepository.save(newOrderItem);

        OrderItemResponse orderItemResponse = orderItemMapper.toOrderItemResponse(newOrderItem);
        orderItemResponse.setProduct(productDTO);

        return orderItemResponse;
    }

    public OrderResponse findById(String id) {
        Order existingOrder = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not existing."));

        return mapper.toOrderResponse(existingOrder);
    }

    public OrderResponse updateStatusOrder(String id) {
        Order existingOrder = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not existing."));

        existingOrder.setStatus("proved");
        repository.save(existingOrder);

        return mapper.toOrderResponse(existingOrder);
    }

    public List<OrderResponse> findAll() {
        List<Order> orderList = repository.findAll();

        return mapper.toResponses(orderList);
    }

    public List<OrderResponse> findByUserId(String userId) {
        List<Order> orderList = repository.findByUserId(userId);

        return mapper.toResponses(orderList);
    }

}
