package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.StaffStatus;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.exceptions.NoSuchElementException;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.RandomGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final RandomGenerator randomGenerator;
    private final MailingService mailingService;

    @Transactional
    public StaffGetDto createStaff(Staff staff) {
        return staffMapper.staffToGetDto(staffRepository.save(staff));
    }

    public void sendVerificationEmail(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find staff with given id"));
        log.info("Found requested staff to send verification email to, generating verification token...");

        String verificationToken = randomGenerator.string(32);
        staff.setVerificationToken(verificationToken);
        staffRepository.save(staff);
        log.info("Verification token saved to database, preparing to send verification email...");

        mailingService.sendVerificationEmail(staff.getEmail(), verificationToken);
    }

    public void verifyEmail(Long id, String verificationToken) {
        Staff staff = staffRepository.findByIdAndVerificationToken(id, verificationToken)
                .orElseThrow(() -> new NoSuchElementException("Cannot find staff with given id or verification token"));
        staff.setStatus(StaffStatus.VERIFIED);
        staffRepository.save(staff);
        log.info("Staff verified.");
    }
}
