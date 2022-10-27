package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;

import com.courtcanva.ccfranchise.dtos.orders.OrderListGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListPostDto;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModeGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @ResponseStatus(HttpStatus.OK)
    public SuburbListAndFilterModeGetDto addDutyAreas(@RequestBody @Valid SuburbListAndFilterModePostDto suburbListAndFilterModePostDto, @PathVariable(value = "franchiseeId") Long franchiseeId) {
        return franchiseeService.dutyAreas(suburbListAndFilterModePostDto, franchiseeId);
    }


    @GetMapping ("/{franchiseeId}/acceptedorders/")
    public OrderListGetDto acceptedOrdersList (@PathVariable(value = "franchiseeId") Long franchiseeId) {
        return franchiseeService.findFranchiseeAcceptedOrders(franchiseeId);
    }

    @PostMapping("/{franchiseeId}/accept_orders")
    @ResponseStatus(HttpStatus.OK)
    public OrderListGetDto acceptOrders(@RequestBody @Valid OrderListPostDto orderListPostDto,
                                        @PathVariable(value = "franchiseeId") Long franchiseeId) {
        return franchiseeService.acceptOrders(orderListPostDto);
    }

}
