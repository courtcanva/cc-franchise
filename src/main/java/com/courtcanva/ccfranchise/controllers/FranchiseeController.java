package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseeDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import com.courtcanva.ccfranchise.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class FranchiseeController {

    @Autowired
    private FranchiseeService franchiseeService;
    @Autowired
    private StaffService staffService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUpFranchiseeAndStaff(@RequestBody @Valid FranchiseeDto franchiseeDto) {

        var staffId = staffService.createStaffOfFranchisee(franchiseeDto);

        var franchiseeId = franchiseeService.createFranchisee(franchiseeDto);

        return "success  " + staffId + franchiseeId;
    }

}
