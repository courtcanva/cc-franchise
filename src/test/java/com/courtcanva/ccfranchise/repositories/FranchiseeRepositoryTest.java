package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void shouldReturnFranchiseeWhenFranchiseeIdIsExist() {

        Long franchiseeId = 1L;

        Franchisee franchisee = TestHelper.createFranchisee();
        franchiseeRepository.save(franchisee);

        assertEquals(franchisee.getAbn(),franchiseeRepository.findFranchiseeById(franchiseeId).getAbn());
    }
}