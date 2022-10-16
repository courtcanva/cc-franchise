package com.courtcanva.ccfranchise.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderGetDto {
    private String status;
//    private OffsetDateTime createdTime;
//    private String customerId;
//    private String contactInformation;
//    private String postcode;
//    private BigDecimal totalAmount;
}
