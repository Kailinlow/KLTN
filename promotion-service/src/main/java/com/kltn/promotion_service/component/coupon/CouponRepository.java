package com.kltn.promotion_service.component.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    List<Coupon> findByPromotionId(String id);
    Optional<Coupon> findByCode(String code);
}
