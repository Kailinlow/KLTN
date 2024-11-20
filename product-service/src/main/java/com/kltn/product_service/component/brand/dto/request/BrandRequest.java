package com.kltn.product_service.component.brand.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class BrandRequest {
    private String name;
    private String information;
}
