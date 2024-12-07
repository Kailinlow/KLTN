package com.kltn.promotion_service.component.promotion.dto.request;

import lombok.*;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePromotionRequest {
    private String code;

    private String description;

    private String imageUrl;

    private String discountType;

    private String startDate;

    private String endDate;
}
