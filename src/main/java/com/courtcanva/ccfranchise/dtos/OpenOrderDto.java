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
public class OpenOrderDto {
    // private Long orderId;
    private OffsetDateTime createdTime;

    private String customerId;
    // TODO: 10/8/22 get customerName from customer table

    private String contactInformation;
    // TODO: 10/8/22 get address from customer table
    // TODO: 10/8/22 get phone from customer table

    private String postcode;

    private BigDecimal totalAmount;

}
