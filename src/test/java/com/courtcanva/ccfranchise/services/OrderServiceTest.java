package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.dtos.orders.OrderPendingPostDto;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.courtcanva.ccfranchise.utils.OrderTestHelper.orders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;


    @BeforeEach
    void setUp() {
        OrderMapper orderMapper = new OrderMapperImpl();
        orderService = new OrderService(
                orderRepository,
                orderMapper
        );
    }

    @Test
    public void shouldReturnAcceptedOrder() {
        when(orderRepository.findOrdersByFranchiseeAndStatusInOrderByStatusAscCreatedTime(
                any(), any(), eq(PageRequest.of(0, 10))
        )).thenReturn(OrderTestHelper.AcceptedOrderList());

        assertEquals("102", orderService.findAcceptedOrdersByFranchisee(FranchiseeTestHelper.createFranchiseeWithId(),
                1).getAcceptedOrders().get(0).getOrderId());
    }


    @Test
    void givenFranchieeId_whenOpenOrdersAvailable_shouldReturnListOfOrders() {
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(1L, OrderStatus.ASSIGNED_PENDING)).thenReturn(orders);
        List<OrderPendingPostDto> firstTenOpenOrdersGetDto = orderService.getFirstTenOpenOrdersById(1L);
        assertTrue(firstTenOpenOrdersGetDto.stream().map(OrderPendingPostDto::getPostcode).toList().containsAll(List.of("3000", "4000")));
        assertTrue(firstTenOpenOrdersGetDto.stream().map(OrderPendingPostDto::getTotalAmount).toList()
                       .containsAll(List.of(BigDecimal.valueOf(3000L), BigDecimal.valueOf(4000L))));
    }

    @Test
    void givenFranchieeId_whenOpenOrdersUnavailable_shouldReturnEmptyList() {
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(1L, OrderStatus.ASSIGNED_PENDING)).thenReturn(new ArrayList<>());
        assertEquals(0, orderService.getFirstTenOpenOrdersById(1L).size());
    }

}
