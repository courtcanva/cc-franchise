package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.OpenOrderGetDto;
import com.courtcanva.ccfranchise.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order dtoToOrder(OpenOrderGetDto openOrderGetDto);

    // @Mapping(target="orderId", source = "id")
    OpenOrderGetDto orderToDto(Order order);
}
