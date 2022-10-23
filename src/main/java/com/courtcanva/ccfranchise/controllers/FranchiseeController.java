package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.OpenOrderGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.exceptions.OrderStatusInvalidException;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/franchisee")
@RequiredArgsConstructor
public class FranchiseeController {

    private final FranchiseeService franchiseeService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public FranchiseeAndStaffDto signUpFranchiseeAndStaff(@RequestBody @Valid FranchiseeAndStaffPostDto franchiseeAndStaffPostDto) {

        return franchiseeService.createFranchiseeAndStaff(franchiseeAndStaffPostDto.getFranchiseePostDto(),
                franchiseeAndStaffPostDto.getStaffPostDto());

    }

    @PostMapping("/{franchiseeId}/service_areas")
    @ResponseStatus(HttpStatus.CREATED)
    public SuburbListGetDto addDutyAreas(@RequestBody @Valid SuburbListPostDto suburbListPostDto, @PathVariable(value = "franchiseeId") Long franchiseeId) {
        return franchiseeService.addDutyAreas(suburbListPostDto, franchiseeId);
    }

    @GetMapping("/{franchiseeId}/orders")
    public List<OpenOrderGetDto> getFirst10OpenOrders(@PathVariable Long franchiseeId, @RequestParam @NotNull String status){
        if (OrderStatus.ASSIGNED_PENDING.getStatus().equals(status)) {
            return franchiseeService.getFirst10OpenOrders(franchiseeId);
        } else {
            throw new OrderStatusInvalidException("order status invalid");
        }
    }

}
