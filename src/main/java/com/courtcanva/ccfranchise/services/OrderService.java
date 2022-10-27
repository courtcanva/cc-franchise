package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderListGetDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderListGetDto getAcceptedOrders (Franchisee franchisee){

        List<Order> allOrders = orderRepository.findOrdersByFranchisee(franchisee);

        List<Order> acceptedOrders = allOrders.stream()
                .filter(order -> order.getStatus().equals(OrderStatus.ACCEPTED))
                .toList();

        return OrderListGetDto.builder()
                .orders(acceptedOrders
                        .stream()
                        .map(orderMapper::orderToGetDto)
                        .collect(Collectors.toList())).build();

    }
}
