package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderAssignmentServiceTest {
    @Mock
    private OrderAssignmentRepository orderAssignmentRepository;

    private FranchiseeService franchiseeService;

    @Test
    public void givenOrderIds_whenFranchiseeRejectOrders_thenUpdateOrdersStatusToReject() {

    }
}
