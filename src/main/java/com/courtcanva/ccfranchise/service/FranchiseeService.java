package com.courtcanva.ccfranchise.service;

import com.courtcanva.ccfranchise.dto.UsernamePasswordDto;
import com.courtcanva.ccfranchise.repository.FranchiseeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FranchiseeService {

    private final FranchiseeRepository franchiseeRepository;
    public boolean loginByUsernamePassword(UsernamePasswordDto usernamePasswordDto) {
        return franchiseeRepository.checkExists(usernamePasswordDto);
    }
}
