package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.OpenOrderResponseDto;
import com.courtcanva.ccfranchise.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDisplayMapper {

    Order dtoToOrder(OpenOrderResponseDto openOrderResponseDto);

    // @Mapping(target="orderId", source = "id")
    OpenOrderResponseDto orderToDto(Order order);
}
