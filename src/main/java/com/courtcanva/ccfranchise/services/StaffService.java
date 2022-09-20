package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Transactional
    public StaffGetDto createStaffWithFranchisee(StaffPostDto staffPostDto, Franchisee franchisee) {

        Staff staff = staffMapper.staffPostDtoToStaff(staffPostDto);

        staff.setFranchisee(franchisee);
        staff.setIsVerified(false);

        return staffMapper.staffToStaffGetDto(staffRepository.save(staff));
    }

}
