package com.courtcanva.ccfranchise.auths;

import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffDetailServiceTest {
    @InjectMocks
    private StaffDetailService staffDetailService;

    @Mock
    private StaffRepository staffRepository;

    @Test
    public void ShouldReturnStaffDetailsSuccessfully() {
        Staff mockStaff = StaffTestHelper.createStaff();
        when(staffRepository.findByEmail(any()))
                .thenReturn(Optional.ofNullable(mockStaff));

        StaffDetail staffDetail = staffDetailService.loadUserByUsername(mockStaff.getEmail());

        assertEquals("666@gmail.com", staffDetail.getUsername());

    }


}