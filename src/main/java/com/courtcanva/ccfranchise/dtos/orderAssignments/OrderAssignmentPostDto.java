package com.courtcanva.ccfranchise.dtos.orderAssignments;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAssignmentPostDto {

    private Franchisee franchisee;

    private Order order;

    private OrderAssignmentStatus status;

    private OffsetDateTime assignedTime;

    private OffsetDateTime updatedTime;
}
