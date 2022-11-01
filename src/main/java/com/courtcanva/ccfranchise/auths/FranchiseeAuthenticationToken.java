package com.courtcanva.ccfranchise.auths;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

public class FranchiseeAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final Long franchiseeId;

    public FranchiseeAuthenticationToken(Long franchiseeId, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.franchiseeId = franchiseeId;
    }

    @Override
    public Map<String, Long> getDetails() {
        return Map.of("FranchiseeId", franchiseeId);
    }
}
