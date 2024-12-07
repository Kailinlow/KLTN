package com.kltn.promotion_service.component.coupon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    Page<Coupon> findAll(Pageable pageable);
    Page<Coupon> findByPromotionId(String id, Pageable pageable);
    List<Coupon> findByPromotionId(String id);
    Optional<Coupon> findByCode(String code);
}
