package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderAssignmentTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class OrderAssignmentRepositoryTest {
    @Autowired
    private OrderAssignmentRepository orderAssignmentRepository;
    @Autowired
    private FranchiseeRepository franchiseeRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldReturnOrderAssignmentFindByOrderId() {
        Order order = orderRepository.save(OrderTestHelper.mockAcceptedOrder1());
        Franchisee franchisee = franchiseeRepository.save(FranchiseeTestHelper.createFranchisee());
        OrderAssignment orderAssignment = OrderAssignmentTestHelper.createOrderAssignment();
        orderAssignment.setId(OrderAssignmentId.builder()
                .orderId(order.getId())
                .franchiseeId(franchisee.getId())
                .build());
        orderAssignment.setOrder(order);
        orderAssignment.setFranchisee(franchisee);
        orderAssignmentRepository.save(orderAssignment);
        assertEquals(1L, orderAssignmentRepository.findByOrderIdIn(
                        OrderTestHelper.createOrderListPostDto().getOrders()
                                .stream()
                                .map(OrderPostDto::getId)
                                .collect(Collectors.toList()))
                .get(0).getOrder().getId());
    }
}