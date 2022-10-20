package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.StaffStatus;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffVerifyEmailPostDto;
import com.courtcanva.ccfranchise.exceptions.ExpiredVerificationTokenException;
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
    public StaffGetDto createStaff(Staff staff) {
        Staff savedStaff = staffRepository.save(staff);
        this.sendVerificationEmail(savedStaff.getId());
        return staffMapper.staffToGetDto(savedStaff);
    }

    public void sendVerificationEmail(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find staff with given id"));
        log.info("Found requested staff to send verification email to, generating verification token...");

        String verificationToken = UUID.randomUUID().toString();

        staff.setVerificationToken(verificationToken);
        staff.setVerificationTokenCreatedTime(OffsetDateTime.now());
        staffRepository.save(staff);
        log.info("Verification token saved to database, preparing to send verification email...");

        mailingService.sendVerificationEmail(staff.getEmail(), verificationToken);
    }

    public void verifyEmail(StaffVerifyEmailPostDto staffVerifyEmailPostDto) throws ExpiredVerificationTokenException {
        Staff verifyEmailDto = staffMapper.verifyEmailPostDtoToStaff(staffVerifyEmailPostDto);
        Staff staff = staffRepository.findByVerificationToken(verifyEmailDto.getVerificationToken())
                .orElseThrow(() -> new NoSuchElementException("Cannot find staff with given id or verification token"));

        OffsetDateTime verificationTokenCreatedTime = staff.getVerificationTokenCreatedTime();
        if (verificationTokenCreatedTime.isAfter(OffsetDateTime.now().minus(24, ChronoUnit.HOURS))) {
            log.info("Verification token is valid, staff is verified");
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
