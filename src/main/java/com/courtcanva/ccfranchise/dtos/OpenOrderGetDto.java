package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
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
