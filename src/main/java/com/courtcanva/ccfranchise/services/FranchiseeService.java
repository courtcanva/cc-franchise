package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.DutyAreaFilterMode;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModeGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.SuburbMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FranchiseeService {
    private final FranchiseeRepository franchiseeRepository;

    private final FranchiseeMapper franchiseeMapper;

    private final StaffMapper staffMapper;

    private final StaffService staffService;

    private final PasswordEncoder passwordEncoder;

    private final SuburbService suburbService;

    private final SuburbMapper suburbMapper;
    

    @Transactional(noRollbackFor = MailingClientException.class)
    public FranchiseeAndStaffDto createFranchiseeAndStaff(FranchiseePostDto franchiseePostDto, StaffPostDto staffPostDto) {

        if (franchiseeExists(franchiseePostDto.getAbn())) {

            log.debug("franchisee with abn: {} already exist", franchiseePostDto.getAbn());

            throw new ResourceAlreadyExistException("franchisee already exist");

        }

        Franchisee franchisee = franchiseeRepository
                .save(franchiseeMapper.postDtoToFranchisee(franchiseePostDto));


        Staff staff = staffMapper.postDtoToStaff(staffPostDto);
        staff.setPassword(passwordEncoder.encode(staffPostDto.getPassword()));
        staff.setFranchisee(franchisee);

        StaffGetDto staffGetDto = staffService.createStaff(staff);

        return FranchiseeAndStaffDto.builder()
                .staffGetDto(staffGetDto)
                .franchiseeGetDto(franchiseeMapper.franchiseeToGetDto(franchisee))
                .build();
    }

    public SuburbListAndFilterModeGetDto dutyAreas(SuburbListAndFilterModePostDto suburbListAndFilterModePostDto, Long franchiseeId) {

        return suburbListAndFilterModePostDto.getFilterMode().equals(DutyAreaFilterMode.INCLUDE) ? addDutyAreas(suburbListAndFilterModePostDto, franchiseeId) : null;

    }


    @Transactional
    public SuburbListAndFilterModeGetDto addDutyAreas(SuburbListAndFilterModePostDto suburbListAndFilterModePostDto, Long franchiseeId) {


        Optional<Franchisee> optionalFranchisee = findFranchiseeById(franchiseeId);

        Franchisee franchisee = optionalFranchisee.orElseThrow(() -> {

            log.debug("franchisee with id: {} is not exist", franchiseeId);

            return new ResourceNotFoundException("franchisee id is not exist");

        });

        List<Suburb> allSuburbs = suburbService.findSuburbBySscCodes(suburbListAndFilterModePostDto.getSuburbs()
                .stream()
                .map(SuburbPostDto::getSscCode)
                .collect(Collectors.toList()));

        franchisee.addDutyAreas(allSuburbs);
        franchiseeRepository.save(franchisee);

        return SuburbListAndFilterModeGetDto.builder()
                .filterMode(suburbListAndFilterModePostDto.getFilterMode())
                .suburbs(allSuburbs
                        .stream()
                        .map(suburbMapper::suburbToGetDto)
                        .collect(Collectors.toList()))
                .build();


    }


    public boolean franchiseeExists(String abn) {

        return franchiseeRepository.existsFranchiseeByAbn(abn);

    }

    public Optional<Franchisee> findFranchiseeById(Long franchiseeId) {

        return franchiseeRepository.findFranchiseeById(franchiseeId);

    }


}
