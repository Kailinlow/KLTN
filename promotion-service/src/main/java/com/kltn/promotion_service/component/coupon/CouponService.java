package com.kltn.promotion_service.component.coupon;

import com.kltn.promotion_service.component.coupon.dto.request.CouponRequest;
import com.kltn.promotion_service.component.coupon.dto.response.CouponResponse;
import com.kltn.promotion_service.component.coupon.mapper.CouponMapper;
import com.kltn.promotion_service.component.promotion.Promotion;
import com.kltn.promotion_service.component.promotion.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository repository;
    private final CouponMapper mapper;

    private final PromotionRepository promotionRepository;

    public CouponResponse create(CouponRequest request) {
        Promotion existingPromotion = promotionRepository.findById(request.getPromotionId())
                .orElseThrow(() -> new RuntimeException("Promotion is not existing."));

        Coupon coupon = mapper.toCoupon(request);
        coupon.setPromotion(existingPromotion);

        Coupon savedCoupon = repository.save(coupon);

        return mapper.toCouponResponse(savedCoupon);
    }

    public CouponResponse findById(String id) {
        Coupon exisitingCoupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon is not existing."));

        return mapper.toCouponResponse(exisitingCoupon);
    }

    public List<CouponResponse> findAll() {
        List<Coupon> couponList = repository.findAll();

        return mapper.toCouponResponseList(couponList);
    }

    public List<CouponResponse> findByPromotionId(String id) {
        List<Coupon> couponList = repository.findByPromotionId(id);

        return mapper.toCouponResponseList(couponList);
    }

    public CouponResponse findByCode(String code) {
        Coupon existingCoupon = repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Coupon is not existing."));

        return mapper.toCouponResponse(existingCoupon);
    }

    public CouponResponse useCoupon(String id) {
        Coupon exisitingCoupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon is not existing."));

        exisitingCoupon.setStatus(CouponStatus.USED);
        repository.save(exisitingCoupon);

        return mapper.toCouponResponse(exisitingCoupon);
    }

    public CouponResponse expiredCoupon(String id) {
        Coupon exisitingCoupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon is not existing."));

        exisitingCoupon.setStatus(CouponStatus.EXPIRED);
        repository.save(exisitingCoupon);

        return mapper.toCouponResponse(exisitingCoupon);
    }

    public CouponResponse update(CouponRequest request, String id) {
        Coupon exisitingCoupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon is not existing."));

        mapper.updateCouponFromRequest(request, exisitingCoupon);

        Coupon updatedCoupon = repository.save(exisitingCoupon);

        return mapper.toCouponResponse(updatedCoupon);
    }

    public void delete(String id) {
        Coupon exisitingCoupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon is not existing."));

        repository.delete(exisitingCoupon);
    }
}
