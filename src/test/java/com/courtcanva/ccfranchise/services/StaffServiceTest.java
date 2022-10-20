package com.courtcanva.ccfranchise.services;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

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
                mailingService
        );
    }

    @Test
    void givenStaffPostDto_whenCreateStaff_shouldNotThrowError() {
        StaffService spyStaffService = spy(staffService);
        Staff staff = StaffTestHelper.createStaff();
        when(staffRepository.save(any())).thenReturn(staff);
        doNothing().when(spyStaffService).sendVerificationEmail(any());

        StaffGetDto staffResult = spyStaffService.createStaff(staff);
        assertEquals(1232L, staffResult.getStaffId());
        verify(spyStaffService, times(1)).sendVerificationEmail(staff.getId());
    }

    @Test
    void givenStaffIde_whenRequestToSendVerificationEmail_shouldNotThrowError() {
        Staff staff = StaffTestHelper.createStaff();
        when(staffRepository.findById(staff.getId())).thenReturn(Optional.ofNullable(staff));
        staffService.sendVerificationEmail(staff.getId());
        ArgumentCaptor<Staff> staffArgumentCaptor = ArgumentCaptor.forClass(Staff.class);

        verify(staffRepository, times(1)).save(staffArgumentCaptor.capture());
        assertNotNull(staffArgumentCaptor.getValue().getVerificationToken());
        assertNotNull(staffArgumentCaptor.getValue().getVerificationTokenCreatedTime());
        verify(mailingService, times(1)).sendVerificationEmail(eq(staff.getEmail()), any());
    }

    @Test
    void givenVerificationTokenAndEncodedEmail_whenVerifyEmail_shouldNotThrowError() {
        Staff staff = StaffTestHelper.createStaffForRepository();
        when(staffRepository.findOneByVerificationTokenAndEmail(any(), any())).thenReturn(Optional.ofNullable(staff));
        staffService.verifyEmail(UUID.randomUUID().toString(), Base64.encodeAsString(staff.getEmail().getBytes()));

        verify(staffRepository, times(1)).save(any());
    }
}