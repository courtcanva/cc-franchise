package com.courtcanva.ccfranchise.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAcceptedListGetDto {

    private int PageNumber;
    private List<OrderAcceptedGetDto> acceptedOrders;
}
