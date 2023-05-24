package com.example.opentickets.models.enums;

public enum Permission {

    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    Permission(String permission){
        this.permission = permission;
    }
}
