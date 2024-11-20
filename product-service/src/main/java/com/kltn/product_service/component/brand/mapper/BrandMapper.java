package com.kltn.product_service.component.brand.mapper;

import com.kltn.product_service.component.brand.Brand;
import com.kltn.product_service.component.brand.dto.request.BrandRequest;
import com.kltn.product_service.component.brand.dto.response.BrandResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
//    @Mapping(target = )
    Brand toBrand(BrandRequest request);

    BrandResponse toBrandResponse(Brand brand);
}
