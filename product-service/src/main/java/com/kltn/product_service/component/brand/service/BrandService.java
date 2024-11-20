package com.kltn.product_service.component.brand.service;

import com.kltn.product_service.component.brand.Brand;
import com.kltn.product_service.component.brand.BrandRepository;
import com.kltn.product_service.component.brand.dto.request.BrandRequest;
import com.kltn.product_service.component.brand.dto.response.BrandResponse;
import com.kltn.product_service.component.brand.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper mapper;

    @Autowired
    public BrandService(BrandRepository brandRepository, BrandMapper mapper) {
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }


    public BrandResponse createBrand(BrandRequest request) {
//        Brand brand = Brand.builder()
//                .name(request.name())
//                .information(request.information())
//                .build();
        Brand brand = mapper.toBrand(request);

        brandRepository.save(brand);

        return mapper.toBrandResponse(brand);
    }

    public List<BrandResponse> findAllBrand() {
        return List.of(
                BrandResponse.builder()
                        .name("asas")
                        .information("asdad")
                        .build()
        );
    }

    public BrandResponse findBrand(String id) {
        Optional<Brand> brandOptional = Optional.ofNullable(brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found")));

        if (brandOptional.isEmpty());
        return BrandResponse.builder()
                .name(brandOptional.get().getName())
                .information(brandOptional.get().getInformation())
                .build();
    }

}
