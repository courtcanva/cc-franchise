package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.annotations.AustralianNumber;
import com.courtcanva.ccfranchise.constants.AUState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseePostDto {

    @NotBlank(message = "Franchisee's business name is mandatory")
    private String businessName;

    @NotBlank(message = "Franchisee's legal entity name is mandatory")
    private String legalEntityName;

    @NotBlank(message = "Franchisee's contact number is mandatory")
    @AustralianNumber
    private String contactNumber;

    @NotBlank(message = "Franchisee's abn is mandatory")
    @Pattern(regexp = "[0-9]{11}", message = "Incorrect ABN format")
    private String abn;

    private String businessAddress;

    @NotNull(message = "Franchisee's state is mandatory")
    private AUState state;

    private int postcode;

}
