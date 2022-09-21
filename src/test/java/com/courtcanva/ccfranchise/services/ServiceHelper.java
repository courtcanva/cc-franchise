package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceHelper {
    protected StaffPostDto mockStaffPostDto;
    protected FranchiseePostDto mockFranchiseePostDto;
    protected Franchisee mockFranchisee;
    protected Staff mockStaff;
    protected StaffGetDto mockStaffGetDto;
    protected FranchiseeGetDto mockFranchiseeGetDto;
    protected FranchiseeAndStaffGetDto mockFranchiseeAndStaffGetDto;
    protected FranchiseeAndStaffPostDto mockFranchiseeAndStaffPostDto;

    @BeforeEach
    protected void setUp() {
        mockStaffPostDto = StaffPostDto.builder()
                .residentialAddress("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state(AUState.ACT)
                .postcode(3213)
                .password("Bfasd1999324")
                .phoneNumber("31232131")
                .firstName("first")
                .lastName("last")
                .build();

        mockFranchisee = Franchisee.builder()
                .id(1234L)
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("1231232")
                .build();
        mockFranchiseePostDto = FranchiseePostDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .entityName("AAAA info")
                .state(AUState.ACT)
                .abn("12312123111")
                .build();
        mockStaffGetDto = StaffGetDto.builder()
                .staffId(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();

        mockStaff = Staff.builder()
                .id(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();

        mockFranchiseeAndStaffPostDto = FranchiseeAndStaffPostDto.builder()
                .franchiseePostDto(mockFranchiseePostDto)
                .staffPostDto(mockStaffPostDto)
                .build();
    }

}
