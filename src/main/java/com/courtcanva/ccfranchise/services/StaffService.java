package com.courtcanva.ccfranchise.services;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.constants.StaffStatus;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.exceptions.ExpiredVerificationTokenException;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final MailingService mailingService;

    public StaffGetDto createStaff(Staff staff) {
        Staff savedStaff = staffRepository.save(staff);
        sendVerificationEmail(savedStaff.getId());
        return staffMapper.staffToGetDto(savedStaff);
    }

    public Boolean emailExists(String email) {
        Boolean isExisted = staffRepository.existsStaffByEmail(email);
        if (Boolean.TRUE.equals(isExisted)) {
            throw new ResourceAlreadyExistException("Email already existed");
        }
        return isExisted;
    }

    public void sendVerificationEmail(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find staff with given id " + id));

        String verificationToken = UUID.randomUUID().toString();

        staff.setVerificationToken(verificationToken);
        staff.setVerificationTokenCreatedTime(OffsetDateTime.now());
        staffRepository.save(staff);

        mailingService.sendVerificationEmail(staff.getEmail(), verificationToken);
    }

    public void verifyEmail(String token, String email) throws ExpiredVerificationTokenException {
        String decodedEmail = new String(Base64.decode(email.getBytes()));
        Staff staff = staffRepository.findOneByVerificationTokenAndEmail(token, decodedEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find staff with given email [" + decodedEmail + "] or verification token [" + token + "]"));

        OffsetDateTime verificationTokenCreatedTime = staff.getVerificationTokenCreatedTime();
        if (verificationTokenCreatedTime.isAfter(OffsetDateTime.now().minus(24, ChronoUnit.HOURS))) {
            staff.setStatus(StaffStatus.VERIFIED);

            staff.setVerificationToken(null);
            staff.setVerificationTokenCreatedTime(null);
            staffRepository.save(staff);
        } else {
            staff.setVerificationToken(null);
            staff.setVerificationTokenCreatedTime(null);
            staffRepository.save(staff);

            throw new ExpiredVerificationTokenException("This verification token has expired.");
        }
    }
}
