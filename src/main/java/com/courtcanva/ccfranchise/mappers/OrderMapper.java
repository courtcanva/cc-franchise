package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.orders.OrderAcceptedGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order postDtoToOrder(OrderPostDto orderPostDto);

    OrderGetDto orderToGetDto(Order order);

    OrderAcceptedGetDto orderToAcceptedGetDto (Order order);
}
