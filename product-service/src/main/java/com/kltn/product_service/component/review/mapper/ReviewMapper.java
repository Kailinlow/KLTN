package com.kltn.product_service.component.review.mapper;

import com.kltn.product_service.component.review.Review;
import com.kltn.product_service.component.review.dto.request.ReviewRequest;
import com.kltn.product_service.component.review.dto.response.ReviewResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewRequest request);
    ReviewResponse toReviewResponse(Review review);
}
