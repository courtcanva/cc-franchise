package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.models.Franchisee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseeAndOrderNumber {

    private Franchisee franchisee;

    private Integer currentOrderNum;
}
