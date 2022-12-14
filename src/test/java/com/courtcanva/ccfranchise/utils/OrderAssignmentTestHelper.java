package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.models.OrderAssignment;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderAssignmentTestHelper {

    public static OrderAssignment createOrderAssignment() {

        return OrderAssignment.builder()
                .assignedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .updatedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .status(OrderAssignmentStatus.ASSIGNED)
                .order(OrderTestHelper.createAssignedOrder())
                .franchisee(FranchiseeTestHelper.createFranchiseeWithDutyAreas())
                .build();
    }

    public static OrderAssignment createAcceptedOrderAssignment() {

        return OrderAssignment.builder()
                .assignedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .updatedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .status(OrderAssignmentStatus.ACCEPTED)
                .order(OrderTestHelper.createAssignedOrder())
                .franchisee(FranchiseeTestHelper.createFranchiseeWithDutyAreas())
                .build();
    }

    public static List<OrderAssignment> orderAssignmentList() {
        List<OrderAssignment> orders = new ArrayList<>();
        orders.add(0, createOrderAssignment());
        return orders;
    }

    public static List<OrderAssignment> orderAcceptedAssignmentList() {
        List<OrderAssignment> orders = new ArrayList<>();
        orders.add(0, createAcceptedOrderAssignment());
        return orders;
    }


    public static Set<OrderAssignment> orderAssignmentSet() {

        Set<OrderAssignment> orderAssignmentSet = new HashSet<>();
        orderAssignmentSet.add(createOrderAssignment());
        return orderAssignmentSet;

    }

}
