package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.orderAssignments.OrderAssignmentGetDto;
import com.courtcanva.ccfranchise.dtos.orderAssignments.OrderAssignmentPostDto;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderAssignmentMapper {

    OrderAssignment postDtoToOrderAssignment(OrderAssignmentPostDto orderAssignmentPostDto);

    OrderAssignmentGetDto orderAssignmentToGetDto(OrderAssignment orderAssignment);
}
