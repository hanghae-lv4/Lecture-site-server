package com.hanghae.lecturesite.entity;

import lombok.Getter;

@Getter
public enum MemberRoleEnum {
    ADMIN(Authority.ADMIN),  // ADMIN 권한
    USER(Authority.USER);  // USER 권한

    private final String authority;

    MemberRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}