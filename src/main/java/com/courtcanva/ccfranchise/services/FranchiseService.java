package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.model.Franchise;
import com.courtcanva.ccfranchise.model.Staff;
import com.courtcanva.ccfranchise.repositories.FranchiseRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utility.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.utility.mappers.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;

    private final FranchiseeMapper franchiseeMapper;

    public Long createFranchise(FranchiseDto franchiseDto) {

        Franchise franchise = franchiseeMapper.toFranchiseeEntity(franchiseDto);

        var newFranchisee = franchiseRepository.save(franchise);

        return newFranchisee.getId();
    }

    public Franchise findFranchiseByABN(Integer abn){

        return franchiseRepository.findByABN(abn).orElseThrow(RuntimeException::new);
    }
}
