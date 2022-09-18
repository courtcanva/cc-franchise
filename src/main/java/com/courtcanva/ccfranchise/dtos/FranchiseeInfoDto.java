package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.constants.States;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseeInfoDto {

    @NotNull
    private String businessName;

    @NotNull
    private String entityName;

    @NotNull
    private StaffInfoDto staff;

    @NotNull
    private String abn;

    @NotNull
    private String businessAddress;

    @NotNull
    private States state;

    @NotNull
    private int postcode;

}
