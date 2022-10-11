package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.OpenOrderDto;
import com.courtcanva.ccfranchise.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDisplayMapper {

    Order dtoToOrder(OpenOrderDto openOrderDto);

    // @Mapping(target="orderId", source = "id")
    OpenOrderDto orderToDto(Order order);
}
