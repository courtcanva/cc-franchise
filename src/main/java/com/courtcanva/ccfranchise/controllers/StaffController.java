package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.exceptions.ExpiredVerificationTokenException;
import com.courtcanva.ccfranchise.services.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/verification")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendVerificationEmail(@RequestParam Long id) {
        staffService.sendVerificationEmail(id);
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void verifyEmail(@RequestParam String token, @RequestParam(name = "i") String email) throws ExpiredVerificationTokenException {
        staffService.verifyEmail(token, email);
    }
}
