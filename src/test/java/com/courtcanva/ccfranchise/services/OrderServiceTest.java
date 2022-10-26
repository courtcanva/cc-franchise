package com.courtcanva.ccfranchise.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.OpenOrderGetDto;
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
import java.math.BigDecimal;
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
        List<Order> orders = List.of(
            OrderTestHelper.createOrder("101", "3000", 3000L, franchisee),
            OrderTestHelper.createOrder("102", "4000", 4000L, franchisee));
        orderRepository.saveAll(orders);
        orderService = new OrderService(orderRepository, new OrderMapperImpl());
    }

    @Test
    void getOpenOrdersByFranchiseeIdTest() {
        // should return OpenOrderGetDto if id exists in order table
        List<Order> orders = List.of(OrderTestHelper.createOrder("101", "3000", 3000L, franchisee),
            OrderTestHelper.createOrder("102", "4000", 4000L, franchisee));
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(0L, OrderStatus.ASSIGNED_PENDING)).thenReturn(orders);
        when(orderRepository.findFirst10ByFranchiseeIdAndStatus(999L, OrderStatus.ASSIGNED_PENDING)).thenReturn(new ArrayList<>());
        List<OpenOrderGetDto> first10OpenOrdersResponseDto = orderService.getFirst10OpenOrdersById(franchisee.getId());
        assertTrue(first10OpenOrdersResponseDto.stream().map(OpenOrderGetDto::getCustomerId).toList().containsAll(List.of("101", "102")));
        assertTrue(first10OpenOrdersResponseDto.stream().map(OpenOrderGetDto::getPostcode).toList().containsAll(List.of("3000", "4000")));
        assertTrue(first10OpenOrdersResponseDto.stream().map(OpenOrderGetDto::getTotalAmount).toList()
                       .containsAll(List.of(BigDecimal.valueOf(3000L), BigDecimal.valueOf(4000L))));

        // should return empty list if open orders number is 0
        assertEquals(0, orderService.getFirst10OpenOrdersById(franchisee.getId() + 999).size());
    }
}
