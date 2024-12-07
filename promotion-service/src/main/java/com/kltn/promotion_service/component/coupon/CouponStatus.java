package com.kltn.promotion_service.component.coupon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CouponStatus {
    VALID ("valid"),
    EXPIRED ("expired"),
    USED ("used");

    @Getter
    private final String couponStatus;
}
