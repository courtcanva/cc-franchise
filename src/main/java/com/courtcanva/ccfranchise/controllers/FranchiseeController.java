package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndOrderNumber;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModeGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import com.courtcanva.ccfranchise.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import javax.validation.Valid;

@RestController
@RequestMapping("/franchisee")
@RequiredArgsConstructor
public class FranchiseeController {

    private final FranchiseeService franchiseeService;

    private final OrderService orderService;

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

    @PostMapping("/{franchiseeId}/accept_orders")
    @ResponseStatus(HttpStatus.OK)
    public OrderListGetDto acceptOrders(@RequestBody @Valid OrderListPostDto orderListPostDto,
                                        @PathVariable(value = "franchiseeId") Long franchiseeId) {
        return franchiseeService.acceptOrders(orderListPostDto);
    }

    @GetMapping("/{franchiseeId}/pending_orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderGetDto> getFirstTenOpenOrders(@PathVariable Long franchiseeId){
        return orderService.getFirstTenOpenOrdersById(franchiseeId);
    }


    @PostMapping("/getOrders/{sscCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> franchiseeListGetDto(@PathVariable(value = "sscCode") int sscCode, @RequestBody Long orderId){
        return franchiseeService.findMatchedFranchisee(sscCode, orderId);
    }

}
