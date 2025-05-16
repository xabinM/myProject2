package com.example.demo.dto.auth;

import lombok.Getter;

@Getter
public class LoginMemberDto {
    private final Long id;
    private final String username;
    private final String email;

    public LoginMemberDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
