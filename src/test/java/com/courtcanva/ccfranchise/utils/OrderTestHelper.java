package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.dtos.IdDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import java.math.BigDecimal;

public class OrderTestHelper {
    public static IdDto createIdDto(Long mockFranchiseeId) {
        return new IdDto(mockFranchiseeId);
    }

    public static Order getOrder1(Franchisee franchisee) {
        return Order.builder()
                   .orderId("101")
                   .customerId("101")
                   .contactInformation("""
                       {"name": "Adam", "phone": "0404123456", "address": "Unit 1, 10 Queen Street, Richmond 3121"}""")
                   .designInformation("""
                       {"name": "draft version 1"}""")
                   .postcode("3000")
                   .totalAmount(new BigDecimal(3000L))
                   .paidAmount(new BigDecimal(1000L))
                   .unpaidAmount(new BigDecimal(2000L))
                   .status("UNASSIGNED")
                   .franchisee(franchisee)
                   .invoiceLink("http://link.co")
                   .build();
    }


}
