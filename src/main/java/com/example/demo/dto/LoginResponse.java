package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginResponse {
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
