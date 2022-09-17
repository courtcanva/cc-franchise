package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeDto;
import com.courtcanva.ccfranchise.model.Franchisee;
import com.courtcanva.ccfranchise.model.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utility.mappers.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final FranchiseeService franchiseeService;

    public Long createStaffOfFranchise(FranchiseeDto franchiseeDto) {

        Staff staff = staffMapper.toStaffEntity(franchiseeDto.getStaff());
        Franchisee franchisee = franchiseeService.findFranchiseeByABN(franchiseeDto.getABN());
        staff.setFranchisee(franchisee);

        var newStaff = staffRepository.save(staff);

        return newStaff.getId();
    }

}
