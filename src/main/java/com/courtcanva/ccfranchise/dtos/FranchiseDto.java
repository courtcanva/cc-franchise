package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseDto {

    private String businessName;

    private StaffDto staff;

    private String  ABN;

    private String businessAddress;

}
