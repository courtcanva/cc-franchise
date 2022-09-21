package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
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

    private final StaffService staffService;

    @Transactional
    public FranchiseeAndStaffGetDto createFranchiseeAndStaff(FranchiseeAndStaffPostDto franchiseeAndStaffPostDto) {

        Franchisee franchisee = franchiseeRepository
                .save(franchiseeMapper.franchiseePostDtoToFranchisee(franchiseeAndStaffPostDto.getFranchiseePostDto()));

        StaffGetDto staffGetDto = staffService.createStaffWithFranchisee(franchiseeAndStaffPostDto.getStaffPostDto(), franchisee);

        return FranchiseeAndStaffGetDto.builder()
                .staffGetDto(staffGetDto)
                .franchiseeGetDto(franchiseeMapper.franchiseeToFranchiseeGetDto(franchisee))
                .build();
    }

    public Franchisee getFranchiseeById(Long id) {

        return franchiseeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Can't found franchisee with id {}", id);
                    return new ResourceNotFoundException("can't find franchisee");
                });
    }
}
