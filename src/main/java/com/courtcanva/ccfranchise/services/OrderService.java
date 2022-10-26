package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;
    @Transactional
    public OrderGetDto addOrders(OrderPostDto orderPostDto){
        Order order=orderRepository.save(orderMapper.postDtoToOrder(orderPostDto));

        return OrderGetDto.builder()
                .id(order.getId())
                .build();
    }

}
