package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Transactional
    public StaffGetDto createStaff(Staff staff) {

        return staffMapper.staffToGetDto(staffRepository.save(staff));

    }

    public Boolean emailExists(String email) {
        Boolean isExisted = staffRepository.existsStaffByEmail(email);
        if (Boolean.TRUE.equals(isExisted)) {
            throw new ResourceAlreadyExistException("Email already existed");
        }
        return isExisted;
    }

}
