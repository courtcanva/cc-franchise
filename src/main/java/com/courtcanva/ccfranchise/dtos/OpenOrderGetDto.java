package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenOrderGetDto {

    private OffsetDateTime createdTime;

    private String customerId;

    private String contactInformation;

    private String postcode;

    private BigDecimal totalAmount;

}
