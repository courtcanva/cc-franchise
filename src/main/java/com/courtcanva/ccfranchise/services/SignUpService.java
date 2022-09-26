package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final StaffRepository staffRepository;

    /**
     * Check whether user's email has been registered
     * @param email
     * @return
     */
    public Boolean checkEmailIsExisted(String email) {
        Boolean isExisted = staffRepository.existsStaffByEmail(email);
        if (isExisted) {
            throw new ResourceAlreadyExistException("Email already existed");
        }
        return isExisted;
    }
}
