package com.example.demo.dto.auth;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final LoginMemberDto member;
    private final String token;

    public LoginResponse(LoginMemberDto member, String token) {
        this.member = member;
        this.token = token;
    }
}
