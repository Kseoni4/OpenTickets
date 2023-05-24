package com.example.opentickets.models.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {

    USER(Set.of(Permission.USER_READ)),
    ADMIN(Set.of(Permission.USER_WRITE));

    private final Set<Permission> permissionSet;

    UserRole(Set<Permission> permissionSet){
        this.permissionSet = permissionSet;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return permissionSet.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
    }

}
