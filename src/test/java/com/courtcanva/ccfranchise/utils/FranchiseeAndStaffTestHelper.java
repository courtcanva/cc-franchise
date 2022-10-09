package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;

public class FranchiseeAndStaffTestHelper {


    public static FranchiseeAndStaffPostDto createFranchiseeAndStaffPostDto() {

        return FranchiseeAndStaffPostDto.builder()
                .staffPostDto(StaffTestHelper.createStaffPostDto())
                .franchiseePostDto(FranchiseeTestHelper.createFranchiseePostDto())
                .build();
    }
}
