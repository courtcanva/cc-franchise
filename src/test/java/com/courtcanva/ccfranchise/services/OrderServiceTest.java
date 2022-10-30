package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.mappers.OrderMapperImpl;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    private OrderMapper orderMapper;
    @BeforeEach
    void setUp(){
        orderMapper = new OrderMapperImpl();
        orderService = new OrderService(
                orderRepository,
                orderMapper
        );
    }

    @Test
    public void shouldReturnAcceptedOrder(){
        when(orderRepository.findOrdersByFranchiseeAndStatusInOrderByStatusAscCreatedTime(
                any(),any(),eq(PageRequest.of(0,10))
                )).thenReturn(OrderTestHelper.AcceptedOrderList());

        assertEquals("102",orderService.findAcceptedOrdersByFranchisee(FranchiseeTestHelper.createFranchiseeWithId(),
                1).getAcceptedOrders().get(0).getOrderId());
    }
}