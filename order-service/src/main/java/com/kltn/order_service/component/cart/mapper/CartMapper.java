package com.kltn.order_service.component.cart.mapper;

import com.kltn.order_service.component.cart.Cart;
import com.kltn.order_service.component.cart.dto.request.CreateCartRequest;
import com.kltn.order_service.component.cart.dto.request.UpdateCartRequest;
import com.kltn.order_service.component.cart.dto.response.CartResponse;
import com.kltn.order_service.component.cart.dto.response.UserCartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CreateCartRequest request);

    CartResponse toCartResponse(Cart cart);
    UserCartResponse toUserCartResponse(Cart cart);

    List<CartResponse> toCartResponseList(List<Cart> cartList);
    List<UserCartResponse> toUserCartResponseList(List<Cart> cartList);

    void updateCartFromRequest(UpdateCartRequest request, @MappingTarget Cart cart);
}
