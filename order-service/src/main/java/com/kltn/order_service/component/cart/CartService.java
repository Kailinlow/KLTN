package com.kltn.order_service.component.cart;

import com.kltn.order_service.component.cart.dto.request.CreateCartRequest;
import com.kltn.order_service.component.cart.dto.request.UpdateCartRequest;
import com.kltn.order_service.component.cart.dto.response.CartResponse;
import com.kltn.order_service.component.cart.dto.response.UserCartResponse;
import com.kltn.order_service.component.cart.mapper.CartMapper;
import com.kltn.order_service.dto.ProductDTO;
import com.kltn.order_service.dto.UserDTO;
import com.kltn.order_service.repository.ProductClient;
import com.kltn.order_service.repository.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final CartMapper mapper;

    private final UserClient userClient;
    private final ProductClient productClient;

    public CartResponse create(CreateCartRequest request) {
        Cart cart = mapper.toCart(request);

        Cart newCart = repository.save(cart);

        return responseCart(newCart);
    }

    public List<CartResponse> findAll() {
        List<Cart> carts = repository.findAll();

        List<CartResponse> cartResponseList = new ArrayList<>();

        carts.forEach(cart -> {
            cartResponseList.add(responseCart(cart));
        });

        return cartResponseList;
    }

    public List<UserCartResponse> findByUserId(String id) {
        List<Cart> cartList = repository.findByUserId(id);

        List<UserCartResponse> userCartResponseList = new ArrayList<>();

        cartList.forEach(cart -> {
            userCartResponseList.add(responseUserCart(cart));
        });

        return userCartResponseList;
    }

    public CartResponse updateQuantity(String id, UpdateCartRequest request) {
        Cart existingCart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not existed."));

        mapper.updateCartFromRequest(request, existingCart);
        repository.save(existingCart);

        return mapper.toCartResponse(existingCart);
    }

    public UserCartResponse responseUserCart(Cart cart) {
        ProductDTO productDTO = productClient.getById(cart.getProductId());

        return UserCartResponse.builder()
                .id(cart.getId())
                .product(productDTO)
                .quantity(cart.getQuantity())
                .build();
    }

    public CartResponse responseCart(Cart cart) {
        UserDTO userDTO = userClient.getUserById(cart.getUserId());

        ProductDTO productDTO = productClient.getById(cart.getProductId());

        return CartResponse.builder()
                .id(cart.getId())
                .user(userDTO)
                .product(productDTO)
                .quantity(cart.getQuantity())
                .build();
    }

    public void delete(String id) {
        Cart existingCart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not existed."));

        repository.delete(existingCart);
    }
}
