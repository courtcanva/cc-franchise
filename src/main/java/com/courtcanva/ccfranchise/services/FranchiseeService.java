package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeInfoDto;
import com.courtcanva.ccfranchise.dtos.ResponseDto;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.model.Franchisee;
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
    public ResponseDto createFranchiseeAndStaff(FranchiseeInfoDto franchiseeInfoDto) {

        franchiseeRepository.save(franchiseeMapper
                .toFranchiseeEntity(franchiseeInfoDto));

        staffService.createStaff(franchiseeInfoDto.getStaff());

        return ResponseDto.builder()
                .responseCode("201")
                .responseMessage("create new Franchisee with new Staff")
                .build();
    }

    public Franchisee findFranchiseeByABN(String abn) {

        return franchiseeRepository.findByAbn(abn)
                .orElseThrow(()->new ResourceNotFoundException("Franchisee","abn",abn));
    }
}
