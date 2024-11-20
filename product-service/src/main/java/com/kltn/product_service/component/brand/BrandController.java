package com.kltn.product_service.component.brand;

import com.kltn.product_service.component.brand.dto.request.BrandRequest;
import com.kltn.product_service.component.brand.dto.response.BrandResponse;
import com.kltn.product_service.component.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest request){
        return ResponseEntity.ok(brandService.createBrand(request));
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> findAll(){
        return ResponseEntity.ok(brandService.findAllBrand());
    }
}
