package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

public class OrderAssignmentTestHelper {

    public static OrderAssignment createOrderAssignment(){

        return OrderAssignment.builder()
                .assignedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .updatedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .status(OrderAssignmentStatus.ASSIGNED)
                .order(OrderTestHelper.createAssignedOrder())
                .franchisee(FranchiseeTestHelper.createFranchiseeWithDutyAreas())
                .build();
    }

    public static Set<OrderAssignment> orderAssignmentSet(){

        Set<OrderAssignment> orderAssignmentSet = new HashSet<>();
        orderAssignmentSet.add(createOrderAssignment());
        return orderAssignmentSet;

    }

    public static OrderAssignmentId orderAssignmentId(){
        return OrderAssignmentId.builder()
                .franchiseeId(FranchiseeTestHelper.createFranchiseeWithDutyAreas().getId())
                .orderId(OrderTestHelper.Order1().getId())
                .build();
    }
}
