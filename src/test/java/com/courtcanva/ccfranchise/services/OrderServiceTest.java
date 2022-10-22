package com.courtcanva.ccfranchise.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.OpenOrderResponseDto;
import com.courtcanva.ccfranchise.mappers.OrderMapperImpl;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Franchisee franchisee;

    @BeforeEach
    void setUp() {
        List<Order> orders = List.of(OrderTestHelper.createOrder("101", "3000", 3000L, franchisee),
            OrderTestHelper.createOrder("102", "4000", 4000L, franchisee));
        orderRepository.saveAll(orders);
        orderService = new OrderService(orderRepository, new OrderMapperImpl());
    }

    @Test
    void getOpenOrdersByFranchiseeIdTest() {
        // should return OpenOrderResponseDto if id exists in order table
        List<Order> orders = List.of(OrderTestHelper.createOrder("101", "3000", 3000L, franchisee),
            OrderTestHelper.createOrder("102", "4000", 4000L, franchisee));
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(0L, OrderStatus.ASSIGNED_PENDING)).thenReturn(orders);
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(999L, OrderStatus.ASSIGNED_PENDING)).thenReturn(new ArrayList<>());
        List<OpenOrderResponseDto> openOrdersByFranchiseeId = orderService.getFirst10OpenOrdersById(franchisee.getId());
        assertEquals(OrderTestHelper.createOrderResponseDto("101", "3000", 3000L),openOrdersByFranchiseeId.get(0));
        assertEquals(OrderTestHelper.createOrderResponseDto("102", "4000", 4000L),openOrdersByFranchiseeId.get(1));

        // should return empty list if open orders number is 0
        assertEquals(0,orderService.getFirst10OpenOrdersById(franchisee.getId()+999).size());
        // assertThrows(NoAvailableOrderException.class,()->orderService.getFirst10OpenOrdersById(999L));

    }
}
