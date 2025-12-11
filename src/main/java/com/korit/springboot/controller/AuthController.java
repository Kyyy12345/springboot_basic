package com.korit.springboot.controller;

import com.korit.springboot.dto.CreateUserReqDto;
import com.korit.springboot.dto.SigninReqDto;
import com.korit.springboot.dto.SignupReqDto;
import com.korit.springboot.entity.UserEntity;
import com.korit.springboot.mapper.UserMapper;
import com.korit.springboot.security.PrincipalUser;
import com.korit.springboot.service.AuthService;
import com.korit.springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


// AuthenticationController
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupReqDto dto) {
        authService.duplicatedUsername(dto.getUsername());
        authService.signup(dto);
        return ResponseEntity.ok("회원가입 ㅊㅊ");
    }

    @PostMapping("/api/auth/signin")
    public ResponseEntity<Map<String, String>> signin(@Valid @RequestBody SigninReqDto dto) {

        return ResponseEntity.ok(Map.of("accessToken", authService.signin(dto)));
    }

    @GetMapping("/api/auth/principal")
    public ResponseEntity<PrincipalUser> getPrincipal(@AuthenticationPrincipal PrincipalUser principalUser) {

        return ResponseEntity.ok().build();
    }
}
