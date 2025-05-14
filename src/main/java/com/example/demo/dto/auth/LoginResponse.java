package com.example.demo.dto.auth;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String message;

    public LoginResponse(String message) {
        this.message = message;
    }
}
