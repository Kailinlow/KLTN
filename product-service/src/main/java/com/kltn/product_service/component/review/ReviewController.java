package com.kltn.product_service.component.review;

import com.kltn.product_service.component.review.dto.request.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

}
