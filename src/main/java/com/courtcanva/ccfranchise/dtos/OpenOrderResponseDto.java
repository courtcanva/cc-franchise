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
public class OpenOrderResponseDto {

    private OffsetDateTime createdTime;

    private String customerId;

    private String contactInformation;
    // TODO: <future function> get customerName, address, phone no. from final database (cc-admin repository).

    private String postcode;

    private BigDecimal totalAmount;

}
