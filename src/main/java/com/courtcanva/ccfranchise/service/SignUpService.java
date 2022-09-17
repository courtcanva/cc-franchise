package com.courtcanva.ccfranchise.service;

import com.courtcanva.ccfranchise.repository.FranchiseeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final FranchiseeRepository franchiseeRepository;

    public Boolean verifyEmail(String email) {
        return franchiseeRepository.existsFranchiseeByEmail(email);
    }
}
