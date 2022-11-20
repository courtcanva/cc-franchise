package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.constants.OrderStatus;
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
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderAssignmentService {

    private final FranchiseeService franchiseeService;

    private final OrderAssignmentRepository orderAssignmentRepository;

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrderAssignment(Order order) {

        OffsetDateTime currentTime = OffsetDateTime.now();
        OffsetDateTime updateTime = OffsetDateTime.now();

        int sscCode = Integer.parseInt(order.getSscCode());

        List<Franchisee> franchiseeList = franchiseeService.findMatchedFranchisee(sscCode, order.getId());


        if (franchiseeList.isEmpty()) {
            log.debug("No available franchisee for sscCode: {}", sscCode);
        }

        OrderAssignmentId orderAssignmentId = buildOrderAssignmentId(franchiseeList.get(0).getId(), order.getId());

        OrderAssignment orderAssignment = buildOrderAssignment(orderAssignmentId, currentTime, updateTime, order, franchiseeList.get(0));

        order.setFranchisee(franchiseeList.get(0));
        order.setStatus(OrderStatus.ASSIGNED_PENDING);

        orderRepository.save(order);
        orderAssignmentRepository.save(orderAssignment);

        log.info("Assigned order {} to franchisee {}", order.getId(), franchiseeList.get(0).getId());

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
}
