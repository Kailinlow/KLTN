package com.kltn.order_service.repository;

import com.kltn.order_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("/user/{id}")
    UserDTO getUserById(@PathVariable("id") String id);
}
