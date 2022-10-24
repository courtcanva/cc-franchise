package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.constants.DutyAreaFilterMode;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModeGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapperImpl;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.mappers.SuburbMapper;
import com.courtcanva.ccfranchise.mappers.SuburbMapperImpl;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import com.courtcanva.ccfranchise.utils.SuburbTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FranchiseeServiceTest {

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private StaffService staffService;

    private FranchiseeService franchiseeService;

    @Mock
    private SuburbService suburbService;

    @Mock
    private SuburbRepository suburbRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        franchiseeRepository.save(FranchiseeTestHelper.createFranchiseeWithId());
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());
    }

    @BeforeEach
    public void setFranchiseeServiceUp() {

        FranchiseeMapper franchiseeMapper = new FranchiseeMapperImpl();
        StaffMapper staffMapper = new StaffMapperImpl();
        SuburbMapper suburbMapper = new SuburbMapperImpl();
        franchiseeService = new FranchiseeService(
                franchiseeRepository,
                franchiseeMapper,
                staffMapper,
                staffService,
                passwordEncoder,
                suburbService,
                suburbMapper
        );
    }

    @Test
    void shouldCreateStaffAndFranchiseeGetDto() {

        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        StaffGetDto staffGetDto = StaffTestHelper.createStaffGetDto();

        FranchiseePostDto franchiseePostDto = FranchiseeTestHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = StaffTestHelper.createStaffPostDto();

        when(franchiseeRepository.save(any())).thenReturn(franchisee);
        when(staffService.createStaff(any())).thenReturn(staffGetDto);

        FranchiseeAndStaffDto franchiseeAndStaffDto = franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto);
        assertEquals(1234L, franchiseeAndStaffDto.getFranchiseeGetDto().getFranchiseeId());
    }

    @Test
    void shouldAddSuburbListGetDto() {

        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        List<Suburb> suburbsListWithFranchisee = SuburbTestHelper.createSuburbsListWithFranchisee();
        Franchisee franchiseeWithDutyAreas = FranchiseeTestHelper.createFranchiseeWithDutyAreas();
        SuburbListAndFilterModePostDto suburbListAndFilterModePostDto = SuburbTestHelper.createSuburbListPostDtoWithIncludeMode();

        Optional<Franchisee> optionalFranchisee = FranchiseeTestHelper.createOptionalFranchisee();

        when(franchiseeRepository.findFranchiseeById(any())).thenReturn(optionalFranchisee);
        when(suburbService.findSuburbBySscCodes(any())).thenReturn(suburbsListWithFranchisee);
        doNothing().when(optionalFranchisee.orElse(null)).addDutyAreas(suburbsListWithFranchisee);
        when(franchiseeRepository.save(any())).thenReturn(franchiseeWithDutyAreas);

        SuburbListAndFilterModeGetDto suburbListAndFilterModeGetDto = franchiseeService.addDutyAreas(suburbListAndFilterModePostDto, franchisee.getId());
        assertEquals(12287L, suburbListAndFilterModeGetDto.getSuburbs().get(1).getSscCode());
        assertEquals(DutyAreaFilterMode.INCLUDE, suburbListAndFilterModeGetDto.getFilterMode());
    }

    @Test
    void shouldReturnNullWhenFilterModeIsNotInclude() {
        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        SuburbListAndFilterModePostDto suburbListAndFilterModePostDto = SuburbTestHelper.createSuburbListPostDtoWithExcludeMode();

        SuburbListAndFilterModeGetDto suburbListAndFilterModeGetDto = franchiseeService.addDutyAreas(suburbListAndFilterModePostDto, franchisee.getId());
        assertNull(suburbListAndFilterModeGetDto);
    }


    @Test
    void shouldReturnTrueWhenResourceAlreadyExist() {

        String franchiseeAbn = "124";

        when(franchiseeRepository.existsFranchiseeByAbn(franchiseeAbn))
                .thenReturn(true);

        assertTrue(franchiseeService.franchiseeExists(franchiseeAbn));
    }

    @Test
    void shouldThrowResourceAlreadyExistException() {


        FranchiseePostDto franchiseePostDto = FranchiseeTestHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = StaffTestHelper.createStaffPostDto();

        when(franchiseeRepository.existsFranchiseeByAbn(any()))
                .thenReturn(true);

        assertThrows(ResourceAlreadyExistException.class,
                () -> franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto));

    }

    @Test
    void shouldThrowResourceNotFoundExist() {
        SuburbListAndFilterModePostDto suburbListAndFilterModePostDto = SuburbTestHelper.createSuburbListPostDtoWithIncludeMode();

        when(franchiseeRepository.findFranchiseeById(any()))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> franchiseeService.addDutyAreas(suburbListAndFilterModePostDto, 6L));

    }

}