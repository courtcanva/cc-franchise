package com.courtcanva.ccfranchise.dtos.orders;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAcceptedGetDto {

    private Long id;
    private String orderId;
    private OrderStatus status;
    private String contactInformation;
    private String designInformation;
}
