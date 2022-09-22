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
public class FranchiseeGetDto {

    private Long franchiseeId;
    private String abn;
    private String businessAddress;
    private String businessName;
    private String legalEntityName;
    private Boolean isVerified;
    private int postcode;
    private AUState state;
}
