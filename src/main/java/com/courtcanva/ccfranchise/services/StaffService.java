package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.model.Franchise;
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
    private final FranchiseService franchiseService;

    public Long createStaffOfFranchise(FranchiseDto franchiseDto) {

        Staff staff = staffMapper.toStaffEntity(franchiseDto.getStaff());
        Franchise franchise =franchiseService.findFranchiseByABN(franchiseDto.getABN());
        staff.setFranchise(franchise);

        var newStaff = staffRepository.save(staff);

        return newStaff.getId();
    }

}
