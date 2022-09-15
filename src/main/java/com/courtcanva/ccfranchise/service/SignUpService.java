package com.courtcanva.ccfranchise.service;

import com.courtcanva.ccfranchise.dto.Franchisee;
import com.courtcanva.ccfranchise.dto.RequiredEmail;
import com.courtcanva.ccfranchise.dto.Result;
import com.courtcanva.ccfranchise.enums.ResultsEnum;
import com.courtcanva.ccfranchise.repository.FranchiseeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService {
    @Autowired
    private FranchiseeRepository franchiseeRepository;

    public Result verifyEmail(RequiredEmail requiredEmailPwd) {
        String email = requiredEmailPwd.getEmail();
        Optional<Franchisee> franchisee = franchiseeRepository.findOneByEmail(email);
        if (!franchisee.isPresent())
            return Result.builder().success(true).code(ResultsEnum.EMAIL_EXISTS.getCode()).msg(ResultsEnum.EMAIL_EXISTS.getMessage()).build();
        return Result.builder().success(true).code(ResultsEnum.SUCCESS.getCode()).msg(ResultsEnum.SUCCESS.getMessage()).build();
    }
}
