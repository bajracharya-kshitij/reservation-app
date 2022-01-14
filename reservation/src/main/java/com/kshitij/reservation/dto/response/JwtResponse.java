package com.kshitij.reservation.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class JwtResponse extends LoggedUserInfoResponse {

    private String token;

    private String type = "Bearer";

    public JwtResponse(String accessToken, String name, String email, List<GrantedAuthority> authorities) {
        super(name, email, authorities);
        this.token = accessToken;
    }

    public String getAccessToken() {
        return this.token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return this.type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
