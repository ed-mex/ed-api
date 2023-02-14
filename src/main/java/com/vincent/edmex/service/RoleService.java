package com.vincent.edmex.service;

import com.vincent.edmex.config.UserRole;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    public boolean isAdmin(String role) {
        return role.equals(UserRole.ROLE_ADMIN.getAuthority());

    }

    public boolean isUser(String role) {
        return role.equals(UserRole.ROLE_ADMIN.getAuthority());

    }

    public boolean isEsp(String role) {
        return role.equals(UserRole.ROLE_ADMIN.getAuthority());

    }
}
