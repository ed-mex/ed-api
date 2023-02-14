package com.vincent.edmex.config;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_ESP;

    @Override
    public String getAuthority() {
        return name();
    }
}
