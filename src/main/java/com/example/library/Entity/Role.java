package com.example.library.Entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

public enum Role {

    User(Set.of(Permission.READER)), AUTHOR(Set.of(Permission.READER, Permission.AUTHOR)), ADMIN(Set.of(Permission.READER, Permission.AUTHOR, Permission.ADMIN));

    final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        return permissions.stream().map( n ->new SimpleGrantedAuthority(n.permission)).toList();
    }
}
