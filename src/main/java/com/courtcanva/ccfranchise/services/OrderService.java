package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderPendingPostDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderPendingPostDto> getFirstTenOpenOrdersById(Long franchiseeId) {
        List<Order> firstTenOpenOrders = orderRepository.findFirst10ByFranchiseeIdAndStatus(franchiseeId, OrderStatus.ASSIGNED_PENDING);
        return firstTenOpenOrders.stream().map(orderMapper::orderPendingPostDto).toList();
    }
}
