package com.kltn.order_service.repository;

import com.kltn.order_service.dto.CouponDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "promotion-service", url = "http://localhost:8082")
public interface CouponClient {
    @GetMapping("/coupon/{id}")
    CouponDTO getById(@PathVariable("id") String id);
}
