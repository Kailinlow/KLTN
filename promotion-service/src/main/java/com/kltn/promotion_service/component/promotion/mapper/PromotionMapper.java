package com.kltn.promotion_service.component.promotion.mapper;

import com.kltn.promotion_service.component.promotion.Promotion;
import com.kltn.promotion_service.component.promotion.dto.request.CreatePromotionRequest;
import com.kltn.promotion_service.component.promotion.dto.request.UpdatePromotionRequest;
import com.kltn.promotion_service.component.promotion.dto.response.PromotionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    Promotion toPromotion(CreatePromotionRequest request);

    PromotionResponse toPromotionResponse(Promotion promotion);
    List<PromotionResponse> toPromotionResponseList(List<Promotion> promotionList);

    void updatePromotionFromRequest(UpdatePromotionRequest request, @MappingTarget Promotion promotion);
}
