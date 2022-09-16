package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.repositories.FranchiseRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    private final StaffRepository staffRepository;

    public void createFranchise(FranchiseDto franchiseDto) {

        StaffDto staffDto = franchiseDto.getStaff();


    }
}
