package com.kltn.promotion_service.component.promotion.dto.response;

import com.kltn.promotion_service.component.coupon.dto.response.CouponResponse;
import lombok.*;

import java.util.List;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponse {
    private String id;

    private String code;

    private String description;

    private String imageUrl;

    private String discountType;

    private String startDate;

    private String endDate;

    private List<CouponResponse> couponList;
}
