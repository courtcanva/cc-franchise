package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.services.SuburbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suburbs")
@RequiredArgsConstructor
public class SuburbController {

    private final SuburbService suburbService;

    @GetMapping
    public SuburbListGetDto getAllSuburbs() {

        return suburbService.findAllSuburbs();
    }

}
