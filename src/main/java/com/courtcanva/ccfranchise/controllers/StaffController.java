package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
@Validated
public class StaffController {
    private final StaffService staffService;

    @GetMapping("/emails/{email}")
    public Boolean emailExists(@PathVariable("email") @Email(message = "Your email format is invalid.") String email) {
        return staffService.emailExists(email);
    }
}
