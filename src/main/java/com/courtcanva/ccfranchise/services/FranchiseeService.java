package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FranchiseeService {
    private final FranchiseeRepository franchiseeRepository;

    private final FranchiseeMapper franchiseeMapper;

    private final StaffService staffService;

    @Transactional
    public FranchiseeAndStaffGetDto createFranchiseeAndStaff(FranchiseeAndStaffPostDto franchiseeAndStaffPostDto) {

        Franchisee franchisee = franchiseeRepository
                .save(franchiseeMapper.franchiseePostDtoToFranchisee(franchiseeAndStaffPostDto.getFranchiseePostDto()));
        franchisee.setIsVerified(false);

        StaffGetDto staffGetDto = staffService.createStaffWithFranchisee(franchiseeAndStaffPostDto.getStaff(), franchisee);

        return FranchiseeAndStaffGetDto.builder()
                .staffGetDto(staffGetDto)
                .franchiseeGetDto(franchiseeMapper.franchiseeToFranchiseeGetDto(franchisee))
                .build();
    }

}
