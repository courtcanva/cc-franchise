package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeListGetDto;
import com.courtcanva.ccfranchise.dtos.orderAssignments.OrderAssignmentPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListGetDto;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.OrderAssignmentMapper;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;
import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final OrderAssignmentMapper orderAssignmentMapper;

    private final FranchiseeService franchiseeService;

    private final OrderAssignmentRepository orderAssignmentRepository;


    public List<OrderGetDto> getFirstTenOpenOrdersById(Long franchiseeId) {
        List<Order> firstTenOpenOrders = orderRepository.findFirst10ByFranchiseeIdAndStatus(franchiseeId, OrderStatus.ASSIGNED_PENDING);
        return firstTenOpenOrders.stream().map(orderMapper::orderToGetDto).toList();
    }


    private OrderAssignmentId buildOrderAssignmentId(Long franchiseeId, Long orderId) {
        return OrderAssignmentId.builder()
                .franchiseeId(franchiseeId)
                .orderId(orderId)
                .build();
    }


    public void assignOneOrder() {

        List<Order> unassignedOrders = orderRepository.findAllUnAssignedOrders();

        if (unassignedOrders.isEmpty()) {
            log.debug("No unassigned order");
            throw new ResourceNotFoundException("No unassigned order");
        }

        Order order = unassignedOrders.get(0);

        OffsetDateTime currentTime = OffsetDateTime.now();
        OffsetDateTime updateTime = OffsetDateTime.now();

        int sscCode = Integer.parseInt(unassignedOrders.get(0).getSscCode());

        List<Franchisee> franchiseeList = franchiseeService.findFranchiseeByIds(franchiseeService.findMatchedFranchisee(sscCode, order.getId()))
                .stream()
                .sorted((Comparator.comparing(Franchisee::getBusinessName)))
                .toList();

        if (franchiseeList.isEmpty()) {
            log.debug("No available franchisee for sscCode: {}", sscCode);
            throw new ResourceNotFoundException("No available franchisee");
        }

        OrderAssignment orderAssignment = new OrderAssignment(OrderAssignmentStatus.ASSIGNED, currentTime, updateTime, order, franchiseeList.get(0));


        order.setFranchisee(franchiseeList.get(0));
        order.setStatus(OrderStatus.ASSIGNED_PENDING);

        orderRepository.save(order);
        orderAssignmentRepository.save(orderAssignment);


    }

}
