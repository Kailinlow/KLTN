package com.kltn.promotion_service.component.promotion;

import com.kltn.promotion_service.component.coupon.Coupon;
import com.kltn.promotion_service.component.coupon.CouponRepository;
import com.kltn.promotion_service.component.coupon.mapper.CouponMapper;
import com.kltn.promotion_service.component.promotion.dto.request.CreatePromotionRequest;
import com.kltn.promotion_service.component.promotion.dto.request.UpdatePromotionRequest;
import com.kltn.promotion_service.component.promotion.dto.response.PromotionResponse;
import com.kltn.promotion_service.component.promotion.mapper.PromotionMapper;
import com.kltn.promotion_service.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository repository;
    private final PromotionMapper mapper;

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    private PromotionResponse toPromotionResponse(Promotion promotion) {
        PromotionResponse promotionResponse = mapper.toPromotionResponse(promotion);

        List<Coupon> couponList = couponRepository.findByPromotionId(promotion.getId());

        promotionResponse.setCouponList(couponMapper.toCouponResponseList(couponList));

        return promotionResponse;
    }

    public PromotionResponse create(CreatePromotionRequest request) {
        Promotion promotion = mapper.toPromotion(request);

        Promotion savedPromotion = repository.save(promotion);

        return mapper.toPromotionResponse(savedPromotion);
    }

    public PromotionResponse findById(String id) {
        Promotion existingPromotion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion is not existing."));

        return mapper.toPromotionResponse(existingPromotion);
    }

    public PageResponse<PromotionResponse> findAll(int page, int size) {
        List<Promotion> promotionList = repository.findAll();

        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repository.findAll(pageable);

        return PageResponse.<PromotionResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().map(mapper::toPromotionResponse).toList())
                .build();
    }

    public PromotionResponse update(UpdatePromotionRequest request, String id) {
        Promotion existingPromotion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion is not existing."));

        mapper.updatePromotionFromRequest(request, existingPromotion);

        Promotion updatedPromotion = repository.save(existingPromotion);

        return mapper.toPromotionResponse(updatedPromotion);
    }

    public void delete(String id) {
        Promotion existingPromotion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion is not existing."));

        List<Coupon> couponList = couponRepository.findByPromotionId(id);

        if (!couponList.isEmpty()) {
            couponRepository.deleteAll(couponList);
        }

        repository.delete(existingPromotion);
    }
}
