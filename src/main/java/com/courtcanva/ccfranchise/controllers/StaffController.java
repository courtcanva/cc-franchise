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

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/send-verification-email")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendVerificationEmail(@RequestParam Long id) {
        log.info("Received request to send verification email to staff.");
        staffService.sendVerificationEmail(id);
    }

    @PostMapping("/verify-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void verifyEmail(@RequestBody @Valid StaffVerifyEmailPostDto staffVerifyEmailPostDto) throws ExpiredVerificationTokenException {
        log.info("Received request to verify email of staff.");
        staffService.verifyEmail(staffVerifyEmailPostDto);
    }
}
