package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAssignmentService {
    private final OrderAssignmentRepository orderAssignmentRepository;

    @Transactional
    public void rejectAllExpriedOrders() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime rejectTime = now.minusHours(48);
        List<OrderAssignment> orderAssignments = orderAssignmentRepository.findOrderAssignmentByCreatedTimeBefore(rejectTime);
        orderAssignments.forEach(orderAssignment -> orderAssignment.setStatus(OrderAssignmentStatus.REJECTED));
        orderAssignmentRepository.saveAll(orderAssignments);
    }
}