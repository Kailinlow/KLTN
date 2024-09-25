package com.kltn.product_service.component.brand.service;

import com.kltn.product_service.component.brand.Brand;
import com.kltn.product_service.component.brand.BrandRepository;
import com.kltn.product_service.component.brand.dto.request.BrandRequest;
import com.kltn.product_service.component.brand.dto.response.BrandResponse;
import com.kltn.product_service.component.brand.mapper.BrandMapper;
import com.kltn.product_service.component.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

}
