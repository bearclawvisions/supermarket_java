package com.bearclawvisions.api.helpers;

import com.bearclawvisions.principal.CurrentUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwtAuthToken extends JwtAuthenticationToken {
    private final CurrentUser principal;

    public CustomJwtAuthToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, CurrentUser principal) {
        super(jwt, authorities);
        this.principal = principal;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
