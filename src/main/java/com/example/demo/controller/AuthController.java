package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.dto.auth.LoginRequest;
import com.example.demo.dto.auth.LoginResponse;
import com.example.demo.dto.auth.SignupRequest;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            String token = authService.login(request.getUsername(), request.getPassword());

            ResponseCookie cookie = ResponseCookie.from("jwt", token)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(60 * 60)
                    .sameSite("Strict")
                    .build();

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(new LoginResponse("로그인 성공"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
        try {
            authService.signup(request);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkLogin(HttpServletRequest request) {
        if (authService.isLogin(request)) {
            System.out.println("check : 여기냐 1");
            return ResponseEntity.ok().build();
        }
        System.out.println("check : 여기냐 2");
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie deleteCookie = ResponseCookie.from("jwt", "")
                        .httpOnly(true)
                        .secure(false)
                        .path("/")
                        .maxAge(0)
                        .sameSite("Strict")
                        .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .build();
    }
}
