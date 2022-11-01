package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderAssignmentRepository orderAssignmentRepository;

    @Transactional
    public int rejectAllExpriedOrders() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime rejectTime = now.minusHours(48);
        int count = orderAssignmentRepository.rejectAllExpiredOrders(rejectTime);
        return count;
    }
}