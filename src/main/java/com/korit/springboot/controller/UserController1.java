package com.korit.springboot.controller;

import com.korit.springboot.dto.CreateUserReqDto;
import com.korit.springboot.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// 1
@RestController //controller 클래스 만드는순간 달기
public class UserController1 {

    @Autowired
    private UserService userService;


    private String username = "test12";
    private String password = "1234";

    @GetMapping("/info")
    public ResponseEntity<String> printInfo() {
        return ResponseEntity.ok("UserController!!!"); //스태틱메서드 ok
    }


    /*@GetMapping("/users")
    public ResponseEntity<Map<String, String>> users(HttpServletResponse response) {
        response.setStatus(400);
        response.setContentType("application/json");
        return ResponseEntity.status(200).body(Map.of("username", username, "password", password));

    }*/

    @GetMapping("/users")
    public ResponseEntity<Map<String, String>> users(HttpServletResponse response) {

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("username", username, "password", password));

    }
    //집가서 한숨자고~아프지말고~~~



    @PostMapping("/users")
    public ResponseEntity<?> create(@Valid  @RequestBody CreateUserReqDto dto) {
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }



}
