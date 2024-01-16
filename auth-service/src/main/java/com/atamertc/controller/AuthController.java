package com.atamertc.controller;

import com.atamertc.dto.request.LoginRequestDto;
import com.atamertc.dto.request.RegisterRequestDto;
import com.atamertc.dto.response.TokenResponseDto;
import com.atamertc.repository.entity.Auth;
import com.atamertc.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<Auth> registerUser(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(service.registerUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> doLogin(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(service.doLogin(dto));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Auth>> findAllAuth(String token) {
        return ResponseEntity.ok(service.findAllAuth(token));
    }

    @GetMapping("/mesaj")
    public ResponseEntity<String> apideneme() {
        return ResponseEntity.ok("Api duzgunce calisiyor");
    }



}
