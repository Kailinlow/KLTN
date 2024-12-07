package com.kltn.promotion_service.component.coupon;

import com.kltn.promotion_service.component.coupon.dto.request.CouponRequest;
import com.kltn.promotion_service.component.coupon.dto.response.CouponResponse;
import com.kltn.promotion_service.component.coupon.mapper.CouponMapper;
import com.kltn.promotion_service.component.promotion.Promotion;
import com.kltn.promotion_service.component.promotion.PromotionRepository;
import com.kltn.promotion_service.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public PageResponse<CouponResponse> findAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = repository.findAll(pageable);

        return PageResponse.<CouponResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().map(mapper::toCouponResponse).toList())
                .build();
    }

    public PageResponse<CouponResponse> findByPromotionId(String id, int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = repository.findByPromotionId(id, pageable);

        return PageResponse.<CouponResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().map(mapper::toCouponResponse).toList())
                .build();
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
