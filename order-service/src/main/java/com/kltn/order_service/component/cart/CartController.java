package com.kltn.order_service.component.cart;

import com.kltn.order_service.component.cart.dto.request.CreateCartRequest;
import com.kltn.order_service.component.cart.dto.request.UpdateCartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCartRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findByUserId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateQuantity(@PathVariable String id, @RequestBody UpdateCartRequest request) {
        return ResponseEntity.ok(service.updateQuantity(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>("Cart item  has been deleted successfully.", HttpStatus.OK);
    }
}
