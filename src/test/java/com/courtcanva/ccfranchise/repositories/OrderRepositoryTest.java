package com.courtcanva.ccfranchise.repositories;


import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    private FranchiseeRepository franchiseeRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setOrderRepositoryUp() {
        orderRepository.deleteAll();
        staffRepository.deleteAll();
        franchiseeRepository.deleteAll();
    }

    @Test
    void findByIdIn() {
        orderRepository.save(OrderTestHelper.order1());
        orderRepository.save(OrderTestHelper.order2());
        assertEquals(1L, orderRepository.findByIdIn(
                        OrderTestHelper.createOrderListPostDto().getOrders()
                                .stream()
                                .map(OrderPostDto::getId)
                                .collect(Collectors.toList()))
                .get(0).getId());
    }

    @Test
    void shouldReturnAcceptedOrders() {
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


    @Test
    void givenFranchiseeId_whenOpenOrdersAvailable_shouldReturnFirst10ListOfOrders() {
        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        Franchisee franchiseeFromDb = franchiseeRepository.save(franchisee);
        List<Order> orders = List.of(
            OrderTestHelper.createOrder("101", "3000", 3000L, franchiseeFromDb),
            OrderTestHelper.createOrder("102", "4000", 4000L, franchiseeFromDb));
        orderRepository.saveAll(orders);
        List<Order> ordersFromDb =
            orderRepository.findFirst10ByFranchiseeIdAndStatus(franchiseeFromDb.getId(), OrderStatus.ASSIGNED_PENDING);
        List<Long> ids = orders.stream().map(Order::getId).toList();
        ordersFromDb.forEach(order -> assertTrue(ids.contains(order.getId())));
    }

    @Test
    void givenFranchiseeId_whenOpenOrdersUnavailable_shouldReturnEmptyList() {
        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        Franchisee franchiseeFromDb = franchiseeRepository.save(franchisee);
        List<Order> ordersFromDb =
            orderRepository.findFirst10ByFranchiseeIdAndStatus(franchiseeFromDb.getId(), OrderStatus.ASSIGNED_PENDING);
        assertEquals(0, ordersFromDb.size());
    }

    @Test
    void givenOrderStatus_whenUnassignedOrderIsAvailable_shouldReturnOrderList(){

        orderRepository.save(OrderTestHelper.order1());

        List<Order> orderList = orderRepository.findAllByStatusIs(OrderStatus.UNASSIGNED);
        assertEquals(OrderStatus.UNASSIGNED,orderList.get(0).getStatus());

    }

}