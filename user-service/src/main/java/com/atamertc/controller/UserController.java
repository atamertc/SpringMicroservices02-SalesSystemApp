package com.atamertc.controller;

import com.atamertc.dto.request.UserSaveRequestDto;
import com.atamertc.rabbitmq.model.RegisterUserModel;
import com.atamertc.repository.entity.User;
import com.atamertc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/find-all")
    public ResponseEntity<List<User>> findAllUser() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/get-upper-name")
    public ResponseEntity<String> getUpperName(String name) {
        return ResponseEntity.ok(service.getUpper(name));
    }

    @GetMapping("/clear-name")
    public ResponseEntity<Void> getUpperName() {
        service.clearCache();
        return ResponseEntity.ok().build();
    }

}
