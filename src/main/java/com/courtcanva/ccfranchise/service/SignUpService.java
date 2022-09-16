package com.courtcanva.ccfranchise.service;

import com.courtcanva.ccfranchise.dto.Franchisee;
import com.courtcanva.ccfranchise.dto.RequiredEmail;
import com.courtcanva.ccfranchise.dto.Result;
import com.courtcanva.ccfranchise.enums.EmailEnum;
import com.courtcanva.ccfranchise.exception.EmailVerifyException;
import com.courtcanva.ccfranchise.repository.FranchiseeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService {
    @Autowired
    private FranchiseeRepository franchiseeRepository;

    public Result verifyEmail(RequiredEmail requiredEmailPwd) {
        String email = requiredEmailPwd.getEmail();
        if (!checkFormatValid(email))
            return Result.builder().success(true).code(EmailEnum.EMAIL_NOT_VALID.getCode()).msg(EmailEnum.EMAIL_NOT_VALID.getMessage()).build();
        if (isExisted(email))
            return Result.builder().success(true).code(EmailEnum.EMAIL_EXISTS.getCode()).msg(EmailEnum.EMAIL_EXISTS.getMessage()).build();
        return Result.builder().success(true).code(EmailEnum.SUCCESS.getCode()).msg(EmailEnum.SUCCESS.getMessage()).build();
    }

    private boolean isExisted(String email) {
        Optional<Franchisee> franchisee = franchiseeRepository.findOneByEmail(email);
        if (franchisee.isPresent())
            return true;
        return false;
    }

    private boolean checkFormatValid(String email) {
        String tegex = "[a-zA-Z0-9_]+@\\w+(\\.com|\\.cn){1}";
        return email.matches(tegex);
    }
}
