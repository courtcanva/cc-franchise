package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderOpenGetDto;
import com.courtcanva.ccfranchise.mappers.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.courtcanva.ccfranchise.utils.OrderTestHelper.orders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private OrderAssignmentService orderAssignmentService;

    @Mock
    private OrderAssignmentRepository orderAssignmentRepository;

    private FranchiseeService franchiseeService;

    @BeforeEach
    void setUp() {
        orderRepository.saveAll(orders);
    }

    @BeforeEach
    void setOrderServiceUp() {
        OrderMapper orderMapper = new OrderMapperImpl();
        OrderAssigmentMapper orderAssigmentMapper = new OrderAssigmentMapperImpl();
        orderService = new OrderService(orderRepository, orderMapper, franchiseeRepository, orderAssignmentService, orderAssignmentRepository, franchiseeService, orderAssigmentMapper);
    }

    @Test
    void shouldReturnAcceptedOrder() {
        when(franchiseeRepository.findFranchiseeById(1234L)).
                thenReturn(Optional.ofNullable(FranchiseeTestHelper.createFranchiseeWithId()));
        when(orderRepository.findOrdersByFranchiseeAndStatusInOrderByStatusAscCreatedTime(
                any(), any(), eq(PageRequest.of(0, 10))
        )).thenReturn(OrderTestHelper.acceptedOrderList());

        assertEquals("102", orderService.findAcceptedOrdersByFranchisee(FranchiseeTestHelper.createFranchiseeWithId().getId(),
                1).getAcceptedOrders().get(0).getOrderId());
    }


    @Test
    void givenFranchiseeId_whenOpenOrdersAvailable_shouldReturnListOfOrders() {
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(1L, OrderStatus.ASSIGNED_PENDING)).thenReturn(orders);
        List<OrderOpenGetDto> firstTenOpenOrdersGetDto = orderService.getFirstTenOpenOrdersById(1L);
        assertTrue(firstTenOpenOrdersGetDto.stream().map(OrderOpenGetDto::getPostcode).toList().containsAll(List.of("3000", "4000")));
        assertTrue(firstTenOpenOrdersGetDto.stream().map(OrderOpenGetDto::getTotalAmount).toList()
                .containsAll(List.of(BigDecimal.valueOf(3000L), BigDecimal.valueOf(4000L))));
    }

    @Test
    void givenFranchiseeId_whenOpenOrdersUnavailable_shouldReturnEmptyList() {
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(1L, OrderStatus.ASSIGNED_PENDING)).thenReturn(new ArrayList<>());
        assertEquals(0, orderService.getFirstTenOpenOrdersById(1L).size());
    }


    @Test
    void shouldAssignOrders() {

        orderRepository.save(OrderTestHelper.order1());
        List<Order> unassignedOrders = OrderTestHelper.createUnassignedOrderList();

        when(orderRepository.findAllByStatusIs(OrderStatus.UNASSIGNED)).thenReturn(unassignedOrders);

        orderService.assignOrders();
        assertEquals(OrderStatus.UNASSIGNED, unassignedOrders.get(0).getStatus());

    }
}
