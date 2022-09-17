package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.model.Staff;
import com.courtcanva.ccfranchise.services.FranchiseService;
import com.courtcanva.ccfranchise.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;
    @Autowired
    private StaffService staffService;

    @PostMapping("/signup")
    public String signUpFranchiseAndStaff(@RequestBody @Valid FranchiseDto franchiseDto) {

        var staffId = staffService.createStaffOfFranchise(franchiseDto);

        var franchiseeId = franchiseService.createFranchise(franchiseDto);

        return "success  " + staffId + franchiseeId;
    }

}
