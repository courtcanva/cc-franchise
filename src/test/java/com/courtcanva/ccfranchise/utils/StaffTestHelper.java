package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.StaffVerifyEmailPostDto;
import com.courtcanva.ccfranchise.models.Staff;

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
                .verificationToken("y9BJK1mec5cg6RZCW3XOrTZ2WbyxGIG6")
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

    public static StaffVerifyEmailPostDto createStaffVerifyEmailPostDto() {

        return StaffVerifyEmailPostDto.builder()
                .id("1")
                .verificationToken("y9BJK1mec5cg6RZCW3XOrTZ2WbyxGIG6")
                .build();

    }

}
