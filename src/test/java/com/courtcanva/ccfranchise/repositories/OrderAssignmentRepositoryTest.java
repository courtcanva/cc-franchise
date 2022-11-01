package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.OrderAssignment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.List;

@SpringBootTest
@Rollback(value = false)
public class OrderAssignmentRepositoryTest {
    @Autowired
    private OrderAssignmentRepository orderAssignmentRepository;

    @Test
    public void shouldChangeExpriedOrdersStatus() {
        OffsetDateTime now = OffsetDateTime.now();
        int count = orderAssignmentRepository.rejectAllExpiredOrders(now);
    }

    @Test
    public void shouldFindAllExpiredOrders() {
        OffsetDateTime now = OffsetDateTime.now();
        List<OrderAssignment> allExpiredOrders = orderAssignmentRepository.findAllExpiredOrders(now);
        for (OrderAssignment expiredOrder : allExpiredOrders) {
            System.out.println(expiredOrder);
        }
    }
}
