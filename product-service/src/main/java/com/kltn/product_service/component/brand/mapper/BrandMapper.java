package com.kltn.product_service.component.brand.mapper;

import com.kltn.product_service.component.brand.Brand;
import com.kltn.product_service.component.brand.dto.request.BrandRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBrandFromDto(BrandRequest request, @MappingTarget Brand brand);
}
