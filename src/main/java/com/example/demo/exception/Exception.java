package com.example.demo.exception;

public enum Exception {
    // Auth
    MEMBER_NOT_FOUND_EXCEPTION("사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH_EXCEPTION("비밀번호가 일치하지 않습니다."),
    SIGNUP_USERNAME_DUPLICATE_EXCEPTION("이미 존재하는 아이디입니다."),

    // Recipe
    RECIPE_NOT_EXIST_EXCEPTION("레시피가 존재하지 않습니다.")
    ;


    private static final String PREFIX = "[ERROR] ";
    private final String message;

    Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
