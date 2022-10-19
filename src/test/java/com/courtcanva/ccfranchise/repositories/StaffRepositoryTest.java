package com.courtcanva.ccfranchise.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class StaffRepositoryTest {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private FranchiseeRepository franchiseeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        staffRepository.deleteAll();
        franchiseeRepository.deleteAll();
    }

    @Test
    public void shouldReturnStaffSuccessfully() {

        Franchisee franchisee = franchiseeRepository.save(FranchiseeTestHelper.createFranchisee());
        staffRepository.save(StaffTestHelper.createStaff(franchisee));

        assertEquals("ff",
            staffRepository.findByEmail("666@gmail.com").get().getLastName());

    }


}