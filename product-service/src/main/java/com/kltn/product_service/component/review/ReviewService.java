package com.kltn.product_service.component.review;

import com.kltn.product_service.component.product.Product;
import com.kltn.product_service.component.product.ProductRepository;
import com.kltn.product_service.component.review.dto.request.ReviewRequest;
import com.kltn.product_service.component.review.dto.response.ReviewResponse;
import com.kltn.product_service.component.review.mapper.ReviewMapper;
import com.kltn.product_service.dto.UserDTO;
import com.kltn.product_service.repository.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    private final ProductRepository productRepository;
    private final ReviewMapper mapper;
    private final UserClient userClient;

    public UserDTO findById(String id) {

        return userClient.getUserById(id);
    }

    public ReviewResponse create(ReviewRequest request) {
        UserDTO userDTO = userClient.getUserById(request.getUserId());

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not existed"));

        Review review = mapper.toReview(request);
        repository.save(review);

        ReviewResponse reviewResponse = mapper.toReviewResponse(review);
        reviewResponse.setUser(userDTO);

        return reviewResponse;
    }
}
