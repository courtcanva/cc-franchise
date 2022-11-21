package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderAssignmentServiceTest {

    private OrderAssignmentService orderAssignmentService;

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private FranchiseeService franchiseeService;

    @Mock
    private OrderAssignmentRepository orderAssignmentRepository;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setOrderAssignmentServiceUp(){
        orderAssignmentService = new OrderAssignmentService(
                franchiseeService,
                orderAssignmentRepository,
                orderRepository
        );
    }

    @Test
    void shouldCreateOrderAssignment(){
        franchiseeRepository.save(FranchiseeTestHelper.createFranchiseeWithDutyAreas());
        orderRepository.save(OrderTestHelper.order1());

        Order order = OrderTestHelper.order1();
        List<Franchisee> availableFranchisee = FranchiseeTestHelper.createFranchiseeList();

        when(franchiseeService.findMatchedFranchisee(11344, 1L)).thenReturn(availableFranchisee);

        orderAssignmentService.createOrderAssignment(order);

        assertEquals(OrderStatus.ASSIGNED_PENDING, order.getStatus());

    }
}
