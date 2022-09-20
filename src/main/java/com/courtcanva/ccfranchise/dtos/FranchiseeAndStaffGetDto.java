package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseeAndStaffGetDto {

    private FranchiseeGetDto franchiseeGetDto;

    private StaffGetDto staffGetDto;

}
