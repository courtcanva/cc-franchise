package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.utils.OrderAssignmentTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class OrderAssignmentRepositoryTest {

    @Autowired
    private OrderAssignmentRepository orderAssignmentRepository;

    @BeforeEach
    void setUp() {
        orderAssignmentRepository.deleteAll();
    }

    @Test
    public void givenFranchiseeIdAlreadyExists_whenSaveToRepository_shouldReturnTrue(){

        Long franchiseeId = 1234L;

        OrderAssignment orderAssignment = OrderAssignmentTestHelper.createOrderAssignment();
        orderAssignmentRepository.save(orderAssignment);

        assertEquals(franchiseeId,orderAssignment.getFranchisee().getId());
    }
}
