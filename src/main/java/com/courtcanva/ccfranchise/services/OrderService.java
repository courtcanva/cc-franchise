package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.OpenOrderResponseDto;
import com.courtcanva.ccfranchise.exceptions.NoAvailableOrderException;
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

    public List<OpenOrderResponseDto> getOpenOrdersByFranchiseeId(Long franchiseeId) {
        List<Order> ordersFirst10 = orderRepository.findFirst10ByFranchiseeId(franchiseeId);
        if (ordersFirst10.size() == 0) {
            throw new NoAvailableOrderException("no available orders");
        }
        return ordersFirst10.stream().map(orderMapper::orderToDto).toList();

    }
}
