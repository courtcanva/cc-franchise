package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.*;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.models.Suburb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestHelper {


    public static Staff createStaff() {

        return Staff.builder()
                .id(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
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


    public static SuburbPostDto createSuburbPostDto() {
        return SuburbPostDto.builder()
                .sscCode(11344L)
                .build();
    }

    public static SuburbGetDto createSuburbGetDto() {
        return SuburbGetDto.builder()
                .sscCode(11344L)
                .suburbName("East Albury")
                .postcode(2640)
                .state(AUState.NSW)
                .build();
    }


    public static SuburbListPostDto createSuburbListPostDto() {
        List<SuburbPostDto> suburbs = new ArrayList<>();
        suburbs.add(new SuburbPostDto(11344L));
        suburbs.add(new SuburbPostDto(12287L));
        return SuburbListPostDto.builder().suburbs(suburbs).build();
    }


    public static SuburbListGetDto createSuburbListGetDto() {
        List<SuburbGetDto> suburbs = new ArrayList<>();
        suburbs.add(new SuburbGetDto(11344L, "East Albury", 2640, AUState.NSW));
        suburbs.add(new SuburbGetDto(12287L, "Lavington", 2641, AUState.NSW));
        return SuburbListGetDto.builder().suburbs(suburbs).build();
    }

    public static List<Suburb> suburbs() {
        List<Suburb> suburbs = new ArrayList<>();
        suburbs.add(0,new Suburb(11344L,"East Albury",2640,AUState.NSW,null));
        suburbs.add(1,new Suburb(12287L,"Lavington",2640,AUState.NSW,null));
        return suburbs;
    }

    public static Suburb suburb1(){
        return Suburb.builder()
                .sscCode(11344L)
                .suburbName("East Albury")
                .postcode(2640)
                .state(AUState.NSW)
                .build();
    }

    public static Suburb suburb2(){
        return Suburb.builder()
                .sscCode(12287L)
                .suburbName("Lavington")
                .postcode(2641)
                .state(AUState.NSW)
                .build();
    }




}
