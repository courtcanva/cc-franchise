package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StaffRepositoryTest {
    @Autowired
    private StaffRepository staffRepository;

    @Test
    public void shouldReturnStaffSuccessfully(){
        staffRepository.save(
                TestHelper.createStaff());

        assertEquals("ff",
                staffRepository.findByEmail("666@gmail.com").get().getLastName());

    }


}