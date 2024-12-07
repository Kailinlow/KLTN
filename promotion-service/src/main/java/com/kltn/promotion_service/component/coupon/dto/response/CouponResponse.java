package com.kltn.promotion_service.component.coupon.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponse {
    private String id;

    private String code;

    private BigDecimal discount;

    private String expirationDate;

    private String status;
}
