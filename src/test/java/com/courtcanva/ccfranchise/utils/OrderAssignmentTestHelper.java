package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

public class OrderAssignmentTestHelper {

    public static OrderAssignment createOrderAssignment(){

        OffsetDateTime currentTime = OffsetDateTime.now();
        OffsetDateTime updateTime = OffsetDateTime.now();

        return OrderAssignment.builder()
                .assignedTime(currentTime)
                .updatedTime(updateTime)
                .status(OrderAssignmentStatus.ASSIGNED)
                .order(OrderTestHelper.createAssignedOrder())
                .franchisee(FranchiseeTestHelper.createFranchiseeList().get(0))
                .build();
    }

    public static Set<OrderAssignment> orderAssignmentSet(){

        Set<OrderAssignment> orderAssignmentSet = new HashSet<>();
        orderAssignmentSet.add(createOrderAssignment());
        return orderAssignmentSet;

    }

    public static OrderAssignmentId orderAssignmentId(){
        return OrderAssignmentId.builder()
                .franchiseeId(FranchiseeTestHelper.createFranchiseeList().get(0).getId())
                .orderId(OrderTestHelper.Order1().getId())
                .build();
    }
}
