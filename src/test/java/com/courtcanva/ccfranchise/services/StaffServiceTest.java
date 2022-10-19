package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffVerifyEmailPostDto;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.RandomGenerator;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock
    private RandomGenerator randomGenerator;
    @Mock
    private StaffRepository staffRepository;
    private StaffMapper staffMapper;
    private StaffService staffService;
    @Mock
    private MailingService mailingService;

    @BeforeEach
    public void setStaffServiceUp() {
        staffMapper = new StaffMapperImpl();

        staffService = new StaffService(
                staffRepository,
                staffMapper,
                randomGenerator,
                mailingService
        );
    }

    @Test
    void shouldCreatedStaffSuccessful() {
        StaffService spyStaffService = spy(staffService);
        Staff staff = StaffTestHelper.createStaff();
        when(staffRepository.save(any())).thenReturn(staff);
        doNothing().when(spyStaffService).sendVerificationEmail(any());

        StaffGetDto staffResult = spyStaffService.createStaff(staff);
        assertEquals(1232L, staffResult.getStaffId());
        verify(spyStaffService, times(1)).sendVerificationEmail(staff.getId());
    }

    @Test
    void shouldSendVerificationEmail() {
        Staff staff = StaffTestHelper.createStaff();
        when(staffRepository.findById(any())).thenReturn(Optional.ofNullable(staff));
        when(randomGenerator.string(32)).thenReturn("random generated token");

        staffService.sendVerificationEmail(staff.getId());

        verify(randomGenerator, times(1)).string(32);
        verify(staffRepository, times(1)).save(any());
        verify(mailingService, times(1)).sendVerificationEmail(staff.getEmail(), "random generated token");
    }

    @Test
    void shouldVerifyEmail() {
        StaffVerifyEmailPostDto staffVerifyEmailPostDto = StaffTestHelper.createStaffVerifyEmailPostDto();
        when(staffRepository.findByIdAndVerificationToken(any(), any())).thenReturn(Optional.ofNullable(StaffTestHelper.createStaff()));
        staffService.verifyEmail(staffVerifyEmailPostDto);

        verify(staffRepository, times(1)).save(any());
    }
}