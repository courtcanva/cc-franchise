package com.courtcanva.ccfranchise.services;

import static com.courtcanva.ccfranchise.utils.OrderTestHelper.orders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.mappers.OrderMapperImpl;
import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderAssignmentRepository orderAssignmentRepository;

    @Mock
    private FranchiseeService franchiseeService;

    @BeforeEach
    void setUp() {
        orderRepository.saveAll(orders);
    }

    @BeforeEach
    public void setOrderServiceUp(){
        OrderMapper orderMapper = new OrderMapperImpl();
        orderService = new OrderService(orderRepository,orderMapper,franchiseeService,orderAssignmentRepository);
    }

    @Test
    void givenFranchieeId_whenOpenOrdersAvailable_shouldReturnListOfOrders() {
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(1L, OrderStatus.ASSIGNED_PENDING)).thenReturn(orders);
        List<OrderGetDto> firstTenOpenOrdersGetDto = orderService.getFirstTenOpenOrdersById(1L);
        assertTrue(firstTenOpenOrdersGetDto.stream().map(OrderGetDto::getCustomerId).toList().containsAll(List.of("101", "102")));
        assertTrue(firstTenOpenOrdersGetDto.stream().map(OrderGetDto::getPostcode).toList().containsAll(List.of("3000", "4000")));
        assertTrue(firstTenOpenOrdersGetDto.stream().map(OrderGetDto::getTotalAmount).toList()
                       .containsAll(List.of(BigDecimal.valueOf(3000L), BigDecimal.valueOf(4000L))));
    }

    @Test
    void givenFranchieeId_whenOpenOrdersUnavailable_shouldReturnEmptyList() {
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(1L, OrderStatus.ASSIGNED_PENDING)).thenReturn(new ArrayList<>());
        assertEquals(0, orderService.getFirstTenOpenOrdersById(1L).size());
    }

}
