package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderAcceptedListGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListGetDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderAcceptedListGetDto getAcceptedOrders (Franchisee franchisee, int pageNumber){

        PageRequest pageRequest = PageRequest.of(pageNumber-1, 10);
        List<Order> allOrders = orderRepository.findOrdersByFranchiseeAndStatusInOrderByStatusAscCreatedTime(
                franchisee,List.of(OrderStatus.COMPLETED, OrderStatus.ACCEPTED), pageRequest);

        return OrderAcceptedListGetDto.builder()
                .acceptedOrders(allOrders.stream().map(orderMapper::orderToAcceptedGetDto).collect(Collectors.toList()))
                .PageNumber(pageNumber)
                .build();

    }
}
