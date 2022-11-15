package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.StaffVerifyEmailPostDto;
import com.courtcanva.ccfranchise.exceptions.ExpiredVerificationTokenException;
import com.courtcanva.ccfranchise.services.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
@Validated
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/verification")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendVerificationEmail(@RequestParam Long id) {
        staffService.sendVerificationEmail(id);
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void verifyEmail(@RequestBody @Valid StaffVerifyEmailPostDto staffVerifyEmailPostDto) throws ExpiredVerificationTokenException {
        staffService.verifyEmail(staffVerifyEmailPostDto.getToken(), staffVerifyEmailPostDto.getEmail());
    }

    @GetMapping("/emails/{email}")
    public Boolean emailExists(@PathVariable("email") @Email(message = "Your email format is invalid.") String email) {
        return staffService.emailExists(email);
    }
}
