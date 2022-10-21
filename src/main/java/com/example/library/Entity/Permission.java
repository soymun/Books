package com.example.library.Entity;

public enum Permission {

    READER("READER"), AUTHOR("AUTHOR"), ADMIN("ADMIN");

    final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
