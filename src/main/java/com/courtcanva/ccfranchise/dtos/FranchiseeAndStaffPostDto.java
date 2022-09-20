package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.constants.AUState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseeAndStaffPostDto {

    @NotBlank(message = "Franchisee's business name is mandatory")
    private String businessName;

    @NotBlank(message = "Franchisee's entity name is mandatory")
    private String entityName;

    @Valid
    private StaffPostDto staff;

    @NotBlank(message = "Franchisee's abn is mandatory")
    @Pattern(regexp = "[0-9]{11}", message = "Incorrect ABN format")
    private String abn;

    @NotBlank(message = "Franchisee's business address is mandatory")
    private String businessAddress;

    @NotNull(message = "Franchisee's state is mandatory")
    private AUState state;

    @NotNull(message = "Franchisee's postcode is mandatory")
    private int postcode;

}
