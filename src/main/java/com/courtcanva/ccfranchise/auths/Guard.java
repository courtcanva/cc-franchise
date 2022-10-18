package com.courtcanva.ccfranchise.auths;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.courtcanva.ccfranchise.jwts.JwtUsernameAndPasswordAuthenticationFilter.FRANCHISEE_ID;

@Component
@RequiredArgsConstructor
@Slf4j
public class Guard {

    public boolean checkFranchiseeAccess(Authentication authentication, Long franchiseeId){
        return ifNotAnonymousAuthentication(authentication) && ifStaffBelongsToFranchisee(authentication,franchiseeId);
    }

    private boolean ifNotAnonymousAuthentication(Authentication authentication){
        if(authentication.getPrincipal().equals("anonymousUser")){
            log.debug("Anonymous user, access denied");
            return false;
        }
        return true;

    }

    private boolean ifStaffBelongsToFranchisee(Authentication authentication, Long franchiseeId){
        Map<String,Long> details = (Map<String, Long>) authentication.getDetails();
        Long franchiseeIds = details.get(FRANCHISEE_ID);
        if(!franchiseeIds.equals(franchiseeId)){
            log.debug("Franchisee is not valid, access denied");
            return false;
        }
        return true;
    }

}
