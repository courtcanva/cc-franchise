package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.OrderAssignment;

import java.time.OffsetDateTime;

public class OrderAssignmentTestHelper {
    public static OrderAssignment createOrderAssignmentWithId() {

        return OrderAssignment.builder()
                .id(1L)
                .status("wait")
                .createdTime(OffsetDateTime.now())
                .build();
    }
}
