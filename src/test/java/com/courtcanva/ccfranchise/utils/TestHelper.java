package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Staff;

public class TestHelper {


    public Staff createStaff() {

        return Staff.builder()
                .id(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();
    }

    public StaffGetDto createStaffGetDto() {

        return StaffGetDto.builder()
                .staffId(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();
    }

    public StaffPostDto createStaffPostDto() {

        return StaffPostDto.builder()
                .residentialAddress("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state(AUState.ACT)
                .postcode(3213)
                .password("Bfasdf1123213")
                .phoneNumber("0466277688")
                .firstName("first")
                .lastName("last")
                .build();
    }

    public Franchisee createFranchisee() {

        return Franchisee.builder()
                .id(1234L)
                .contactNumber("234567891")
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("1231232")
                .build();
    }

    public FranchiseePostDto createFranchiseePostDto() {

        return FranchiseePostDto.builder()
                .businessName("AAAAA")
                .contactNumber("234567891")
                .businessAddress("zetland NSWssss")
                .legalEntityName("AAAA info")
                .state(AUState.ACT)
                .abn("12312123111")
                .build();
    }

    public FranchiseeGetDto createFranchiseeGetDto() {

        return FranchiseeGetDto.builder()
                .franchiseeId(1234L)
                .abn("12345678901")
                .build();
    }

    public FranchiseeAndStaffPostDto createFranchiseeAndStaffPostDto() {

        return FranchiseeAndStaffPostDto.builder()
                .staffPostDto(createStaffPostDto())
                .franchiseePostDto(createFranchiseePostDto())
                .build();
    }


}
