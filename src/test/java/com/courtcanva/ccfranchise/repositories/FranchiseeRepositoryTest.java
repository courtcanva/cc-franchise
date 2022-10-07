package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class FranchiseeRepositoryTest {

    @Autowired
    private FranchiseeRepository franchiseeRepository;

    @BeforeEach
    void setUp() {
        franchiseeRepository.deleteAll();
    }

    @Test
    public void shouldReturnTureWhenAbnAlreadyExist() {

        franchiseeRepository.save(
                TestHelper.createFranchisee());

        assertTrue(franchiseeRepository.existsFranchiseeByAbn("12312123111"));


    }

    @Test
    public void shouldReturnFranchiseeWhenFranchiseeIdIsExist() {

        Long franchiseeId = 1L;

        Franchisee franchisee = TestHelper.createFranchisee();
        franchiseeRepository.save(franchisee);

        assertEquals(franchisee.getAbn(), Objects.requireNonNull(franchiseeRepository.findFranchiseeById(franchiseeId).orElse(null)).getAbn());
    }
}