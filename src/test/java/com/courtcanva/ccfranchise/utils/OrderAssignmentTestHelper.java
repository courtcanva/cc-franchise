package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.models.OrderAssignment;

import java.time.OffsetDateTime;

public class OrderAssignmentTestHelper {

    public static OrderAssignment createOrderAssignment(){

        OffsetDateTime currentTime = OffsetDateTime.now();
        OffsetDateTime updateTime = OffsetDateTime.now();

        return OrderAssignment.builder()
                .assignedTime(currentTime)
                .updatedTime(updateTime)
                .status(OrderAssignmentStatus.ASSIGNED)
                .order(OrderTestHelper.OrderWithSscCode())
                .franchisee(FranchiseeTestHelper.createFranchiseeWithDutyAreas())
                .build();
    }
}
