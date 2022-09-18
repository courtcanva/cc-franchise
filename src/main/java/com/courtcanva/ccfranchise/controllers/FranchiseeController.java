package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseeInfoDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/franchisee")
@RequiredArgsConstructor
public class FranchiseeController {

    private final FranchiseeService franchiseeService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> signUpFranchiseeAndStaff(@RequestBody @Valid FranchiseeInfoDto franchiseeInfoDto) {

        franchiseeService.createFranchisee(franchiseeInfoDto);

        return  ResponseEntity.ok("success");
    }

}
