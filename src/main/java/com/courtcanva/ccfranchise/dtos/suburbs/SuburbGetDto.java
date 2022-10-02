package com.courtcanva.ccfranchise.dtos.suburbs;

import com.courtcanva.ccfranchise.constants.AUState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuburbGetDto {
    private Long sscCode;
    private String suburbName;
    private int postcode;
    private AUState state;
}
