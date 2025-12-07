package com.korit.springboot.controller;

import com.korit.springboot.config.BeanConfig;
import com.korit.springboot.dto.CreateUserReqDto;
import com.korit.springboot.service.UserService;
import com.mysql.cj.CharsetMapping;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController2 {


    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<Map<String, Integer>> create(@Valid @RequestBody CreateUserReqDto dto) {
        userService.duplicatedUsername(dto.getUsername());
        int createId = userService.createUser(dto);
        return ResponseEntity.ok(Map.of("createId", createId));

    }


}
