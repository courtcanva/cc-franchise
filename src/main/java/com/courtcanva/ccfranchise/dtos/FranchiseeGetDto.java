package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.constants.AUState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseeGetDto {

    private Long franchiseeId;
    private String abn;
    private String businessAddress;
    private String businessName;
    private String entityName;
    private OffsetDateTime createdTime;
    private OffsetDateTime updatedTime;
    private Boolean isVerified;
    private int postcode;
    private AUState state;
}
