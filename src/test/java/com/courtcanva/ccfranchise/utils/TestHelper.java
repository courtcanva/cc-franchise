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


    public static Staff createStaff() {

        return Staff.builder()
                .id(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .password("Brfasfsad12331")
                .state(AUState.ACT)
                .postcode(1234)
                .phoneNumber("123456789")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();
    }

    public static StaffGetDto createStaffGetDto() {

        return StaffGetDto.builder()
                .staffId(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();
    }

    public static StaffPostDto createStaffPostDto() {

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

    public static Franchisee createFranchiseeWithId() {

        return Franchisee.builder()
                .id(1234L)
                .contactNumber("234567891")
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("12312123111")
                .build();
    }

    public static Franchisee createFranchisee() {

        return Franchisee.builder()
                .contactNumber("234567891")
                .businessName("AAAAA")
                .legalEntityName("COUNTCANVA")
                .state(AUState.ACT)
                .postcode(1234)
                .businessAddress("zetland NSWssss")
                .abn("12312123111")
                .build();
    }

    public static FranchiseePostDto createFranchiseePostDto() {

        return FranchiseePostDto.builder()
                .businessName("AAAAA")
                .contactNumber("234567891")
                .businessAddress("zetland NSWssss")
                .legalEntityName("AAAA info")
                .state(AUState.ACT)
                .abn("12312123111")
                .build();
    }

    public static FranchiseeGetDto createFranchiseeGetDto() {

        return FranchiseeGetDto.builder()
                .franchiseeId(1234L)
                .abn("12345678901")
                .build();
    }

    public static FranchiseeAndStaffPostDto createFranchiseeAndStaffPostDto() {

        return FranchiseeAndStaffPostDto.builder()
                .staffPostDto(createStaffPostDto())
                .franchiseePostDto(createFranchiseePostDto())
                .build();
    }


}
