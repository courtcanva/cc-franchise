package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;
import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
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

    private OrderAssignment buildOrderAssignment(OrderAssignmentId orderAssignmentId, OffsetDateTime assignedTime, OffsetDateTime updateTime, Order order, Franchisee franchisee) {
        return OrderAssignment.builder()
                .id(orderAssignmentId)
                .status(OrderAssignmentStatus.ASSIGNED)
                .assignedTime(assignedTime)
                .updatedTime(updateTime)
                .order(order)
                .franchisee(franchisee)
                .build();
    }


    public void assignOrders() {

        List<Order> unassignedOrders = orderRepository.findAllByStatusIs(OrderStatus.UNASSIGNED);

        if (unassignedOrders.isEmpty()) {
            log.debug("No unassigned order");
            throw new ResourceNotFoundException("No unassigned order");
        }

        for (Order order : unassignedOrders) {

            OffsetDateTime currentTime = OffsetDateTime.now();
            OffsetDateTime updateTime = OffsetDateTime.now();

            int sscCode = Integer.parseInt(order.getSscCode());

            List<Franchisee> franchiseeList = franchiseeService.findFranchiseeByIds(franchiseeService.findMatchedFranchisee(sscCode, order.getId()).stream().map(Franchisee::getId).toList())
                    .stream()
                    .sorted((Comparator.comparing(Franchisee::getBusinessName)))
                    .toList();

            if (franchiseeList.isEmpty()) {
                log.debug("No available franchisee for sscCode: {}", sscCode);
                throw new ResourceNotFoundException("No available franchisee");
            }

            OrderAssignmentId orderAssignmentId = buildOrderAssignmentId(franchiseeList.get(0).getId(), order.getId());

            OrderAssignment orderAssignment = buildOrderAssignment(orderAssignmentId, currentTime, updateTime, order, franchiseeList.get(0));

            order.setFranchisee(franchiseeList.get(0));
            order.setStatus(OrderStatus.ASSIGNED_PENDING);

            orderRepository.save(order);
            orderAssignmentRepository.save(orderAssignment);

        }

    }


}
