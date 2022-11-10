package com.courtcanva.ccfranchise.dtos.orderAssignments;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
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

    private Long franchiseeId;

    private Long orderId;

    private OrderAssignmentStatus status;

    private OffsetDateTime assignedTime;

    private OffsetDateTime updatedTime;
}