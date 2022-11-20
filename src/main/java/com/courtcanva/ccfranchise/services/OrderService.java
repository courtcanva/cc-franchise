package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderAssignmentService orderAssignmentService;


    public List<OrderGetDto> getFirstTenOpenOrdersById(Long franchiseeId) {
        List<Order> firstTenOpenOrders = orderRepository.findFirst10ByFranchiseeIdAndStatus(franchiseeId, OrderStatus.ASSIGNED_PENDING);
        return firstTenOpenOrders.stream().map(orderMapper::orderToGetDto).toList();
    }


    public void assignOrders() {

        List<Order> unassignedOrders = orderRepository.findAllByStatusIs(OrderStatus.UNASSIGNED);

        if (unassignedOrders.isEmpty()) {
            log.info("No unassigned order");
        }

        for (Order order : unassignedOrders) {
            orderAssignmentService.createOrderAssignment(order);
        }

    }


}
