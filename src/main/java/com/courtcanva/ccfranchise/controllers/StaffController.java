package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
@Validated
public class StaffController {
    private final StaffService staffService;

    @GetMapping("/email/exists")
    public Boolean emailExists(@Email @RequestParam("email") @Valid String email) {
        return staffService.emailExists(email);
    }
}
