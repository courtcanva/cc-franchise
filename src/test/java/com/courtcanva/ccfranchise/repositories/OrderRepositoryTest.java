package com.courtcanva.ccfranchise.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.function.Consumer;

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
    void setUp() {
        orderRepository.deleteAll();
        staffRepository.deleteAll();
        franchiseeRepository.deleteAll();
    }

    @Test
    void findFirst10ByFranchiseeIdTest() {
        // should return first 10 orders if franchisee id exists
        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        Franchisee franchiseeFromDb = franchiseeRepository.save(franchisee);
        List<Order> orders = List.of(OrderTestHelper.createOrder("101", "3000", 3000L, franchiseeFromDb),
            OrderTestHelper.createOrder("102", "4000", 4000L, franchiseeFromDb));
        orderRepository.saveAll(orders);

        List<Order> ordersFromDb = orderRepository.findFirst10ByFranchiseeId(franchiseeFromDb.getId());
        List<Long> ids = orders.stream().map(Order::getId).toList();
        ordersFromDb.forEach(order -> assertTrue(ids.contains(order.getId())));

        // should return 0 size List Order
        List<Order> ordersOfOtherId = orderRepository.findFirst10ByFranchiseeId(franchiseeFromDb.getId() + 100);
        assertEquals(0,ordersOfOtherId.size());

    }
}