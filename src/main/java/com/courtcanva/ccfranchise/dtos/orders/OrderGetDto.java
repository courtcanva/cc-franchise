package com.courtcanva.ccfranchise.dtos.orders;

import com.courtcanva.ccfranchise.constants.OrderStatus;
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

    private Long id;

    private String orderId;

    private String customerId;

    private OrderStatus status;

    private String contactInformation;

    private OffsetDateTime createdTime;

    private String postcode;

    private BigDecimal totalAmount;

    private String designInformation;
}
