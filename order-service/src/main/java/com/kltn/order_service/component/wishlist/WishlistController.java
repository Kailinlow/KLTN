package com.kltn.order_service.component.wishlist;

import com.kltn.order_service.component.wishlist.dto.request.WishlistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    public final WishlistService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody WishlistRequest request) {
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

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>("Wishlist has been delete successfully.", HttpStatus.OK);
    }
}
