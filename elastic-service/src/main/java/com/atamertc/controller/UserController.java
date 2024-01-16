package com.atamertc.controller;

import com.atamertc.dto.request.AddUserRequestDto;
import com.atamertc.dto.request.PagingRequestDto;
import com.atamertc.repository.entity.User;
import com.atamertc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elastic/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/save")
    public ResponseEntity<Void> addUser(@RequestBody AddUserRequestDto dto) {
        service.saveUserElastic(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-all-user")
    public ResponseEntity<Iterable<User>> findAllUser() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find-all-vip")
    @PreAuthorize("hasAuthority('VIP')")
    public ResponseEntity<Iterable<User>> findAllVip() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/find-all-page")
    public ResponseEntity<Page<User>> findAllPage(@RequestBody PagingRequestDto dto) {
        return ResponseEntity.ok(service.findAllPage(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long authid) {
        return ResponseEntity.ok(service.deleteByAuthid(authid));
    }






}
