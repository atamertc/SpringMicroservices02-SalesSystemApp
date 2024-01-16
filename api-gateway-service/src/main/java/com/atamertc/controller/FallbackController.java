package com.atamertc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/auth")
    public ResponseEntity<String> fallbackAuth() {
        return ResponseEntity.ok("Auth Servis su anda hizmet verememektedir.");
    }

    @GetMapping("/user")
    public ResponseEntity<String> fallbackUser() {
        return ResponseEntity.ok("User Servis su anda hizmet verememektedir.");
    }

    @GetMapping("/product")
    public ResponseEntity<String> fallbackProduct() {
        return ResponseEntity.ok("Product Servis su anda hizmet verememektedir.");
    }

    @GetMapping("/sale")
    public ResponseEntity<String> fallbackSale() {
        return ResponseEntity.ok("Sale Servis su anda hizmet verememektedir.");
    }
}
