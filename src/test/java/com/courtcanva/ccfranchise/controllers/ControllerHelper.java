package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerHelper {


    protected FranchiseePostDto franchiseePostDto;
    protected StaffPostDto staffPostDto;
    protected FranchiseeAndStaffPostDto franchiseeAndStaffPostDto;

    @BeforeEach
    protected void setUp() {

        franchiseePostDto = FranchiseePostDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .legalEntityName("AAAA info")
                .state(AUState.ACT)
                .abn("12345678901")
                .build();

        staffPostDto = StaffPostDto.builder()
                .residentialAddress("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state(AUState.ACT)
                .postcode(3213)
                .password("Bfasdf1123213")
                .phoneNumber("0466277688")
                .firstName("first")
                .lastName("last")
                .build();
        franchiseeAndStaffPostDto = FranchiseeAndStaffPostDto.builder()
                .franchiseePostDto(franchiseePostDto)
                .staffPostDto(staffPostDto)
                .build();
    }

}
