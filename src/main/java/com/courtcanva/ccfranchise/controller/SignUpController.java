package com.courtcanva.ccfranchise.controller;

import com.courtcanva.ccfranchise.dto.RequiredEmail;
import com.courtcanva.ccfranchise.dto.Result;
import com.courtcanva.ccfranchise.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping
    public Result signUp() {
        return new Result();
    }

    @PostMapping("/verifyEmail")
    public Result verifyEmail(@Valid @RequestBody RequiredEmail requiredEmailPwd) {
        return signUpService.verifyEmail(requiredEmailPwd);
    }
}
