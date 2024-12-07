package com.kltn.order_service.component.wishlist;

import com.kltn.order_service.component.wishlist.dto.request.WishlistRequest;
import com.kltn.order_service.component.wishlist.dto.response.UserWishlistResponse;
import com.kltn.order_service.component.wishlist.dto.response.WishlistResponse;
import com.kltn.order_service.component.wishlist.mapper.WishlistMapper;
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
public class WishlistService {
    private final WishlistRepository repository;
    private final WishlistMapper mapper;

    private final UserClient userClient;
    private final ProductClient productClient;

    public WishlistResponse create(WishlistRequest request) {
        Wishlist wishlist = mapper.toWishlist(request);

        repository.save(wishlist);

        return toWishListResponse(wishlist);
    }

    public List<WishlistResponse> findAll() {
        List<Wishlist> wishlistList = repository.findAll();

        List<WishlistResponse> wishlistResponseList = new ArrayList<>();

        wishlistList.forEach(wishlist -> {
            wishlistResponseList.add(
                    toWishListResponse(wishlist)
            );
        });

        return wishlistResponseList;
    }

    public List<UserWishlistResponse> findByUserId(String id) {
            UserDTO userDTO = userClient.getUserById(id);

            List<Wishlist> wishlistList = repository.findByUserId(id);

            List<UserWishlistResponse> userWishlistResponseList = new ArrayList<>();

            wishlistList.forEach(wishlist -> {
                userWishlistResponseList.add(
                        toUserWishlistResponse(wishlist)
                );
            });

            return userWishlistResponseList;
    }

    public void delete(String id) {
        Wishlist wishlist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not existing."));

        repository.delete(wishlist);
    }

    private WishlistResponse toWishListResponse(Wishlist wishlist) {
        UserDTO userDTO = userClient.getUserById(wishlist.getUserId());

        ProductDTO productDTO = productClient.getById(wishlist.getProductId());

        return new WishlistResponse(
                wishlist.getId(),
                userDTO,
                productDTO
        );
    }

    private UserWishlistResponse toUserWishlistResponse(Wishlist wishlist) {
        ProductDTO productDTO = productClient.getById(wishlist.getProductId());

        return new UserWishlistResponse(
                wishlist.getId(),
                productDTO
        );
    }
}
