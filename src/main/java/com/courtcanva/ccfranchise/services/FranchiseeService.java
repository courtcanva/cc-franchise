package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeInfoDto;
import com.courtcanva.ccfranchise.model.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
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
    public Long createFranchisee(FranchiseeInfoDto franchiseeInfoDto) {

        Franchisee franchisee = franchiseeMapper.toFranchiseeEntity(franchiseeInfoDto);
        Franchisee newFranchisee = franchiseeRepository.save(franchisee);

        staffService.createStaff(franchiseeInfoDto.getStaff());

        return newFranchisee.getId();
    }

    public Franchisee findFranchiseeByABN(String abn){

        return franchiseeRepository.findByAbn(abn).orElseThrow(RuntimeException::new);
    }
}
