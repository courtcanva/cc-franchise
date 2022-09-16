package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.services.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class FranchiseController {

    private FranchiseService franchiseService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUpFranchise(@RequestBody @Valid FranchiseDto franchiseDto){

        franchiseService.createFranchise(franchiseDto);
        return ResponseEntity.ok("signup success");
    }

}
