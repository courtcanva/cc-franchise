package com.courtcanva.ccfranchise.utils;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.StaffVerifyEmailPostDto;
import com.courtcanva.ccfranchise.models.Staff;

import java.time.OffsetDateTime;

public class StaffTestHelper {

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
    public static Staff createStaffWithFranchisee() {

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
                .franchisee(FranchiseeTestHelper.createFranchiseeWithId())
                .build();
    }

    public static Staff createStaffForRepository() {
        return Staff.builder()
                .isVerified(false)
                .firstName("Court")
                .lastName("Canva")
                .password("Brfasfsad12331")
                .state(AUState.NSW)
                .postcode(2000)
                .phoneNumber("123456789")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .verificationToken("3ab3014e-cce3-44b1-9a58-abde53455ccf")
                .verificationTokenCreatedTime(OffsetDateTime.now())
                .build();
    }

    public static Staff createStaffForRepository(String email, OffsetDateTime verificationTokenCreatedTime) {
        return Staff.builder()
                .isVerified(false)
                .firstName("Court")
                .lastName("Canva")
                .password("Brfasfsad12331")
                .state(AUState.NSW)
                .postcode(2000)
                .phoneNumber("123456789")
                .email(email)
                .residentialAddress("fsdfsdafa")
                .verificationToken("a6d45993-7372-4b22-aaf5-9fd7b0101786")
                .verificationTokenCreatedTime(verificationTokenCreatedTime)
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

    public static StaffVerifyEmailPostDto createStaffVerifyEmailPostDto(String token, String email) {
        return StaffVerifyEmailPostDto.builder()
                .token(token)
                .email(Base64.encodeAsString(email.getBytes()))
                .build();
    }
}
