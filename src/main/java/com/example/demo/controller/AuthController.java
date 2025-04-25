package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.SignUpResponse;
import com.example.demo.dto.SignupRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignupRequest request) {
        authService.signup(request);

        return ResponseEntity.ok().build();
    }
}
