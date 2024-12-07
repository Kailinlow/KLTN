package com.kltn.promotion_service.component.promotion;

import com.kltn.promotion_service.component.promotion.dto.request.CreatePromotionRequest;
import com.kltn.promotion_service.component.promotion.dto.request.UpdatePromotionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePromotionRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdatePromotionRequest request) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Promotion has been deleted successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
