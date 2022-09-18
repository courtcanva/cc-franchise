package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.StaffInfoDto;
import com.courtcanva.ccfranchise.model.Franchisee;
import com.courtcanva.ccfranchise.model.Staff;
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
    private final FranchiseeService franchiseeService;

    @Transactional
    public Long createStaff(StaffInfoDto staffInfoDto) {

        Staff staff = staffMapper.toStaffEntity(staffInfoDto);

        Franchisee franchisee = franchiseeService.findFranchiseeByABN(staffInfoDto.getFranchiseAbn());
        staff.setFranchisee(franchisee);

        Staff newStaff = staffRepository.save(staff);

        return newStaff.getId();
    }

}
