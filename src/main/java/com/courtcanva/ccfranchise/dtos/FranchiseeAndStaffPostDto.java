package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseeAndStaffPostDto {

    @Valid
    private FranchiseePostDto franchiseePostDto;

    @Valid
    private StaffPostDto staff;

}
