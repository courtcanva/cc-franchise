package com.courtcanva.ccfranchise.services;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
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
    void givenStaffPostDto_whenCreateStaff_shouldNotThrowError() throws MailingClientException {
        Staff staff = StaffTestHelper.createStaff();
        when(staffRepository.save(staff)).thenReturn(staff);
        when(staffRepository.findById(staff.getId())).thenReturn(Optional.ofNullable(staff));

        StaffGetDto staffResult = staffService.createStaff(staff);
        assertEquals(1232L, staffResult.getStaffId());
    }

    @Test
    void givenStaffId_whenRequestToSendVerificationEmail_shouldNotThrowError() throws MailingClientException {
        Staff staff = StaffTestHelper.createStaff();
        when(staffRepository.findById(staff.getId())).thenReturn(Optional.ofNullable(staff));
        staffService.sendVerificationEmail(staff.getId());
        ArgumentCaptor<Staff> staffArgumentCaptor = ArgumentCaptor.forClass(Staff.class);

        verify(staffRepository).save(staffArgumentCaptor.capture());
        assertNotNull(staffArgumentCaptor.getValue().getVerificationToken());
        assertNotNull(staffArgumentCaptor.getValue().getVerificationTokenCreatedTime());
        verify(mailingService).sendVerificationEmail(eq(staff.getEmail()), eq(staffArgumentCaptor.getValue().getVerificationToken()));
    }

    @Test
    void givenVerificationTokenAndEncodedEmail_whenVerifyEmail_shouldNotThrowError() {
        Staff staff = StaffTestHelper.createStaffForRepository();
        when(staffRepository.findOneByVerificationTokenAndEmail(staff.getVerificationToken(), staff.getEmail())).thenReturn(Optional.ofNullable(staff));
        staffService.verifyEmail(staff.getVerificationToken(), Base64.encodeAsString(staff.getEmail().getBytes()));

        ArgumentCaptor<Staff> staffArgumentCaptor = ArgumentCaptor.forClass(Staff.class);

        verify(staffRepository).save(staffArgumentCaptor.capture());
        assertNull(staffArgumentCaptor.getValue().getVerificationToken());
        assertNull(staffArgumentCaptor.getValue().getVerificationTokenCreatedTime());
    }
}