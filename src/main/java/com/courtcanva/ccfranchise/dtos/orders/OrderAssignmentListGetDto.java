package com.courtcanva.ccfranchise.dtos.orders;

import com.courtcanva.ccfranchise.models.OrderAssignment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAssignmentListGetDto {
    private List<OrderAssignment> orderAssignmentList;
}
