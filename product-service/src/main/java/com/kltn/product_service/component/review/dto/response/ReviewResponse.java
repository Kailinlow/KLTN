package com.kltn.product_service.component.review.dto.response;

import com.kltn.product_service.dto.UserDTO;
import lombok.*;

@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private String id;
    private Integer rating;
    private String comment;
    private String commentStatus;
    private UserDTO user;
}
