package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeDto;
import com.courtcanva.ccfranchise.model.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.utility.mappers.FranchiseeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchiseeService {
    private final FranchiseeRepository franchiseeRepository;

    private final FranchiseeMapper franchiseeMapper;

    public Long createFranchisee(FranchiseeDto franchiseeDto) {

        Franchisee franchisee = franchiseeMapper.toFranchiseeEntity(franchiseeDto);

        var newFranchisee = franchiseeRepository.save(franchisee);

        return newFranchisee.getId();
    }

    public Franchisee findFranchiseeByABN(Integer abn){

        return franchiseeRepository.findByABN(abn).orElseThrow(RuntimeException::new);
    }
}
