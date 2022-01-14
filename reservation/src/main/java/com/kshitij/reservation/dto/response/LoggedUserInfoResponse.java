package com.kshitij.reservation.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class LoggedUserInfoResponse {

    private String name;

    private String email;

    private List<GrantedAuthority> authorities;

    public LoggedUserInfoResponse(String name, String email, List<GrantedAuthority> authorities) {
        this.name = name;
        this.email = email;
        this.authorities = authorities;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
}
