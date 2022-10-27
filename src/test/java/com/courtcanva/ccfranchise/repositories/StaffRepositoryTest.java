package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class StaffRepositoryTest {
    @Autowired
    private StaffRepository staffRepository;

    @Test
    public void givenStaffObject_whenSaveToRepository_shouldReturnStaffSuccessfully() {
        staffRepository.save(
            StaffTestHelper.createStaff());

        assertEquals("ff",
            staffRepository.findByEmail("666@gmail.com").get().getLastName());

    }


}