package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderAcceptedAndCompletedPaginationGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private static final int PAGE_SIZE = 10;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderAssignmentService orderAssignmentService;


    public OrderAcceptedAndCompletedPaginationGetDto findAcceptedOrdersByFranchisee(Franchisee franchisee, int pageNumber) {

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);

        List<Order> acceptedAndCompletedOrders = orderRepository.findOrdersByFranchiseeAndStatusInOrderByStatusAscCreatedTime(
                franchisee, List.of(OrderStatus.COMPLETED, OrderStatus.ACCEPTED), pageRequest);

        return OrderAcceptedAndCompletedPaginationGetDto.builder()
                .acceptedOrders(acceptedAndCompletedOrders.stream().map(orderMapper::orderToGetDto).collect(Collectors.toList()))
                .pageNumber(pageNumber)
                .build();

    }

    public List<OrderGetDto> getFirstTenOpenOrdersById(Long franchiseeId) {
        List<Order> firstTenOpenOrders = orderRepository.findFirst10ByFranchiseeIdAndStatus(franchiseeId, OrderStatus.ASSIGNED_PENDING);
        return firstTenOpenOrders.stream().map(orderMapper::orderToGetDto).toList();

    }


    public void assignOrders() {

        List<Order> unassignedOrders = orderRepository.findAllByStatusIs(OrderStatus.UNASSIGNED);

        if (unassignedOrders.isEmpty()) {
            log.info("No unassigned order");
        }

        for (Order order : unassignedOrders) {
            orderAssignmentService.createOrderAssignment(order);
        }

    }


}
