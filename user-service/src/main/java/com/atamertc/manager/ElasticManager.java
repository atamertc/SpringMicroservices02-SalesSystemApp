package com.atamertc.manager;

import com.atamertc.repository.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "elastic-manager", url = "http://localhost:9096/elastic/user", decode404 = true)
//public interface ElasticManager {
//
//    @PostMapping("/save")
//    ResponseEntity<Void> addUser(@RequestBody User user);
//
//}
