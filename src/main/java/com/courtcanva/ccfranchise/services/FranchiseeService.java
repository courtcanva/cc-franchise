package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FranchiseeService {
    private final FranchiseeRepository franchiseeRepository;

    private final FranchiseeMapper franchiseeMapper;

    private final StaffMapper staffMapper;

    private final StaffService staffService;

    @Transactional
    public FranchiseeAndStaffDto createFranchiseeAndStaff(FranchiseePostDto franchiseePostDto, StaffPostDto staffPostDto) {

        if (checkFranchiseeIsExist(franchiseePostDto.getAbn())) {

            log.error("franchise already exist");

            throw new ResourceAlreadyExistException("franchisee already exist");

        }

        Franchisee franchisee = franchiseeRepository
                .save(franchiseeMapper.postDtoToFranchisee(franchiseePostDto));

        Staff staff = staffMapper.postDtoToStaff(staffPostDto);
        staff.setFranchisee(franchisee);

        StaffGetDto staffGetDto = staffService.createStaff(staff);

        return FranchiseeAndStaffDto.builder()
                .staffGetDto(staffGetDto)
                .franchiseeGetDto(franchiseeMapper.franchiseeToGetDto(franchisee))
                .build();
    }

    public boolean checkFranchiseeIsExist(String abn) {

        return franchiseeRepository.existsFranchiseeByAbn(abn);

    }

}
