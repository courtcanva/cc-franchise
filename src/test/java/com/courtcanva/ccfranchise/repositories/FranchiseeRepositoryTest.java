package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class FranchiseeRepositoryTest {

    @Autowired
    private FranchiseeRepository franchiseeRepository;


    @Test
    public void shouldReturnTureWhenAbnAlreadyExist() {

        franchiseeRepository.save(
                TestHelper.createFranchisee());

        assertTrue(franchiseeRepository.existsFranchiseeByAbn("12312123111"));


    }
}