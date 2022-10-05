package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
@Validated
public class StaffController {
    private final StaffService staffService;

    @GetMapping("/email/{email}")
    public Boolean emailExists(@Email @PathVariable("email") String email) {
        return staffService.emailExists(email);
    }
}
