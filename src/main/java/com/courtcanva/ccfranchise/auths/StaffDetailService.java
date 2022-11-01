package com.courtcanva.ccfranchise.auths;

import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffDetailService implements UserDetailsService {

    private final StaffRepository staffRepository;

    @Override
    public StaffDetail loadUserByUsername(String username) throws UsernameNotFoundException {

        Staff staff = staffRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("user not found with username {}", username);
                    return new UsernameNotFoundException("user not found");
                });

        Set<GrantedAuthority> grantedAuthorities = Stream.of(staff.getBusinessRole()).map(
                        businessRole -> new SimpleGrantedAuthority(businessRole.name()))
                .collect(Collectors.toSet());

        return StaffDetail.builder()
                .username(staff.getEmail())
                .id(staff.getId())
                .franchiseeId(staff.getFranchisee().getId())
                .password(staff.getPassword())
                .grantedAuthorities(grantedAuthorities)
                .isAccountNonLocked(true)
                .isAccountNotExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

    }

}
