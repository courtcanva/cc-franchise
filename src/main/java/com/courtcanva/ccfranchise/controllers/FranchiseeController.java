package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffInfoDto;
import com.courtcanva.ccfranchise.dtos.ResponseDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/franchisee")
@RequiredArgsConstructor
public class FranchiseeController {

    private final FranchiseeService franchiseeService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto signUpFranchiseeAndStaff(@RequestBody @Valid FranchiseeAndStaffInfoDto franchiseeAndStaffInfoDto) {

        return franchiseeService.createFranchiseeAndStaff(franchiseeAndStaffInfoDto);
    }

}
