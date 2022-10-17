package com.courtcanva.ccfranchise.auths;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<String,Set<Long>> details = (Map<String, Set<Long>>) authentication.getDetails();
        Set<Long> franchiseeIds = details.get(FRANCHISEE_ID);
        if(!franchiseeIds.contains(franchiseeId)){
            log.debug("Staff is not belongs to franchisee, access denied");
            return false;
        }
        return true;
    }

}
