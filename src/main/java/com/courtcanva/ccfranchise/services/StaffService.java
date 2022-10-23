package com.courtcanva.ccfranchise.services;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.constants.StaffStatus;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.exceptions.ExpiredVerificationTokenException;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
import com.courtcanva.ccfranchise.exceptions.NoSuchElementException;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public StaffGetDto createStaff(Staff staff) throws MailingClientException {
        Staff savedStaff = staffRepository.save(staff);
        this.sendVerificationEmail(savedStaff.getId());
        return staffMapper.staffToGetDto(savedStaff);
    }

    public void sendVerificationEmail(Long id) throws MailingClientException {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find staff with given id " + id));

        String verificationToken = UUID.randomUUID().toString();

        staff.setVerificationToken(verificationToken);
        staff.setVerificationTokenCreatedTime(OffsetDateTime.now());
        staffRepository.save(staff);

        mailingService.sendVerificationEmail(staff.getEmail(), verificationToken);
    }

    public void verifyEmail(String token, String email) throws ExpiredVerificationTokenException {
        String decodedEmail = new String(Base64.decode(email.getBytes()));
        Staff staff = staffRepository.findOneByVerificationTokenAndEmail(token, decodedEmail)
                .orElseThrow(() -> new NoSuchElementException("Cannot find staff with given email " + decodedEmail + "or verification token"));

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
