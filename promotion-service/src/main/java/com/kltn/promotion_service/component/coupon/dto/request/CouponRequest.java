package com.kltn.promotion_service.component.coupon.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequest {
    private String code;

    private BigDecimal discount;

    private String expirationDate;

    private String promotionId;
}
