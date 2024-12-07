package com.kltn.order_service.component.wishlist.mapper;

import com.kltn.order_service.component.wishlist.Wishlist;
import com.kltn.order_service.component.wishlist.dto.request.WishlistRequest;
import com.kltn.order_service.component.wishlist.dto.response.WishlistResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    Wishlist toWishlist(WishlistRequest request);
}
