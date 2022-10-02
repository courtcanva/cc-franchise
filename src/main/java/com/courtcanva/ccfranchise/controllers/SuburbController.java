package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.services.SuburbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suburb")
@RequiredArgsConstructor
public class SuburbController {

    private final SuburbService suburbService;

    @GetMapping("/all")
    public SuburbListGetDto getAllSuburbs() {

        return suburbService.findAllSuburbs();
    }

}
