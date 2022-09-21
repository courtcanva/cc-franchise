package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.constants.AUState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffGetDto {

    private Long staffId;
    private String email;
    private AUState state;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String residentialAddress;
    private Boolean isVerified;

}
