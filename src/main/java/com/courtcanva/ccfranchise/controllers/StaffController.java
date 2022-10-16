package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.services.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void verifyEmail(@RequestParam Long id, @RequestParam String token) {
        log.info("Received request to verify email of staff.");
        staffService.verifyEmail(id, token);
    }
}
