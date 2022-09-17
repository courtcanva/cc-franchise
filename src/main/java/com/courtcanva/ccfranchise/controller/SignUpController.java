package com.courtcanva.ccfranchise.controller;

import com.courtcanva.ccfranchise.dto.LoginDto;
import com.courtcanva.ccfranchise.dto.Result;
import com.courtcanva.ccfranchise.exception.EmailVerifyException;
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

    /*@PostMapping("/verifyEmail")
    public Result verifyEmail(@Valid @RequestParam RequiredEmail requiredEmailPwd) {
        return signUpService.verifyEmail(requiredEmailPwd);
    }*/

    @GetMapping("/firstVerify")
    public Result firstVerify(@Valid @RequestBody LoginDto loginDto) {
        String email = loginDto.getEmail();
        if (signUpService.verifyEmail(email)) throw new EmailVerifyException();
        return Result.builder().success(true).code(200).msg("success").build();
    }
}
