package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.orders.OrderAssignmentPostDto;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderAssigmentMapper {
    OrderAssignmentPostDto orderAssignmentToPostDto(OrderAssignment orderAssignment);
}
