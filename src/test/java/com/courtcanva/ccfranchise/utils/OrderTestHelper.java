package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.OpenOrderGetDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrderTestHelper {

    public static Order createOrder(String customerId, String postcode, Long totalAmount, Franchisee franchisee) {
        return Order.builder()
                   .orderId("101")
                   .customerId(customerId)
                   .contactInformation("""
                       {"name": "Adam", "phone": "0404123456", "address": "Unit 1, 10 Queen Street, Richmond 3121"}""")
                   .designInformation("""
                       {"name": "draft version 1"}""")
                   .postcode(postcode)
                   .totalAmount(new BigDecimal(totalAmount))
                   .paidAmount(new BigDecimal(1000L))
                   .unpaidAmount(new BigDecimal(2000L))
                   .status(OrderStatus.ASSIGNED_PENDING)
                   .franchisee(franchisee)
                   .invoiceLink("https://link.co")
                   .createdTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                   .updatedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                   .build();
    }

    public static OpenOrderGetDto createOrderResponseDto(String customerId, String postcode, Long totalAmount) {
        return OpenOrderGetDto.builder()
                   .customerId(customerId)
                   .contactInformation("""
                       {"name": "Adam", "phone": "0404123456", "address": "Unit 1, 10 Queen Street, Richmond 3121"}""")
                   .postcode(postcode)
                   .totalAmount(new BigDecimal(totalAmount))
                   .createdTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                   .build();

    }


}
