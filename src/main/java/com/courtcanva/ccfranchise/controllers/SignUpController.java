package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/signUp")
@RequiredArgsConstructor
@Validated
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping("/checkEmail")
    public Boolean checkEmailIsExisted(@Email @RequestParam @Valid String email) {
        return signUpService.checkEmailIsExisted(email);
    }
}
