package com.atamertc.manager;

import com.atamertc.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-manager", url = "http://localhost:9091/user", decode404 = true)
public interface UserManager {

    @PostMapping("/save")
    ResponseEntity<Boolean> saveUser(@RequestBody UserSaveRequestDto dto);


}
