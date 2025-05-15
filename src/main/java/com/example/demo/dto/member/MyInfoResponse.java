package com.example.demo.dto.member;

import lombok.Getter;

@Getter
public class MyInfoResponse {
    private final String username;
    private final String email;

    public MyInfoResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
