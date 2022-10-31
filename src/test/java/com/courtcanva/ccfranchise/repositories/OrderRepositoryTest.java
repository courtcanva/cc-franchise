package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.utils.FranchiseeAndStaffTestHelper;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FranchiseeRepository franchiseeRepository;

    @BeforeEach
    void setOrderRepositoryUp() {
        orderRepository.deleteAll();
        franchiseeRepository.deleteAll();
        orderRepository.save(OrderTestHelper.Order1());
        orderRepository.save(OrderTestHelper.Order2());

    }

    @Test
    void findByIdIn() {
        assertEquals(1L, orderRepository.findByIdIn(
                        OrderTestHelper.createOrderListPostDto().getOrders()
                                .stream()
                                .map(OrderPostDto::getId)
                                .collect(Collectors.toList()))
                .get(0).getId());
    }

    @Test
    void shouldReturnAcceptedOrders() {
/*        Optional<Order> order1 = orderRepository.findById(3L);
        order1.get().setFranchisee(franchiseeRepository.findFranchiseeById(1L).get());
        orderRepository.save(order1.get());*/
        Order order = orderRepository.save(OrderTestHelper.mockAcceptedOrder1());
        Franchisee franchisee = franchiseeRepository.save(FranchiseeTestHelper.createFranchisee());
        order.setFranchisee(franchisee);
        orderRepository.save(order);


        PageRequest pageRequest = PageRequest.of(0, 10);
        assertEquals("111",
                orderRepository
                        .findOrdersByFranchiseeAndStatusInOrderByStatusAscCreatedTime(franchisee,
                                List.of(OrderStatus.ACCEPTED, OrderStatus.COMPLETED),
                                pageRequest).get(0).getOrderId());
    }
}