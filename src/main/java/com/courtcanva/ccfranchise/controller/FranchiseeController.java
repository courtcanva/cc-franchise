package com.courtcanva.ccfranchise.controller;

import com.courtcanva.ccfranchise.dto.UsernamePasswordDto;
import com.courtcanva.ccfranchise.service.FranchiseeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FranchiseeController {

    private static final Logger logger = LoggerFactory.getLogger(FranchiseeController.class);

    private final FranchiseeService franchiseeService;

    @GetMapping(value = "/hello", params = {"username", "password"})
    public String hello(String username, String password) {
        return "hello: " + username + " " + password;
    }

    @PostMapping("/login")
    public String login(@RequestBody UsernamePasswordDto usernamePasswordDto) {
        if (franchiseeService.loginByUsernamePassword(usernamePasswordDto)) {
            return "login success";
        } else {
            return "login failed";
        }
    }

}
