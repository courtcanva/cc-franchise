package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
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
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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


    @BeforeEach
    void setUp() {
        franchiseeRepository.save(TestHelper.createFranchiseeWithId());
        suburbRepository.save(TestHelper.suburb1());
        suburbRepository.save(TestHelper.suburb2());
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
                suburbService,
                suburbMapper
        );
    }

    @Test
    void shouldCreateStaffAndFranchiseeGetDto() {

        Franchisee franchisee = TestHelper.createFranchiseeWithId();
        StaffGetDto staffGetDto = TestHelper.createStaffGetDto();

        FranchiseePostDto franchiseePostDto = TestHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = TestHelper.createStaffPostDto();

        when(franchiseeRepository.save(any())).thenReturn(franchisee);
        when(staffService.createStaff(any())).thenReturn(staffGetDto);

        FranchiseeAndStaffDto franchiseeAndStaffDto = franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto);
        assertEquals(1234L, franchiseeAndStaffDto.getFranchiseeGetDto().getFranchiseeId());
    }

    @Test
    void shouldAddSuburbListGetDto() {

        Franchisee franchisee = TestHelper.createFranchiseeWithId();
        List<Suburb> suburbsListWithFranchisee = TestHelper.createSuburbsListWithFranchisee();
        Franchisee franchiseeWithDutyAreas = TestHelper.createFranchiseeWithDutyAreas();
        SuburbListPostDto suburbListPostDto = TestHelper.createSuburbListPostDto();

        Optional<Franchisee> optionalFranchisee = TestHelper.createOptionalFranchisee();

        when(franchiseeRepository.findFranchiseeById(any())).thenReturn(optionalFranchisee);
        when(suburbService.findSuburbBySscCodes(any())).thenReturn(suburbsListWithFranchisee);
        doNothing().when(optionalFranchisee.orElse(null)).addDutyAreas(suburbsListWithFranchisee);
        when(franchiseeRepository.save(any())).thenReturn(franchiseeWithDutyAreas);

        SuburbListGetDto suburbListGetDto = franchiseeService.addDutyAreas(suburbListPostDto, franchisee.getId());
        assertEquals(12287L, suburbListGetDto.getSuburbs().get(1).getSscCode());
    }


    @Test
    void shouldReturnTrueWhenResourceAlreadyExist() {

        String franchiseeAbn = "124";

        when(franchiseeRepository.existsFranchiseeByAbn(franchiseeAbn))
                .thenReturn(true);

        assertTrue(franchiseeService.ifFranchiseeExists(franchiseeAbn));
    }

    @Test
    void shouldThrowResourceAlreadyExistException() {


        FranchiseePostDto franchiseePostDto = TestHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = TestHelper.createStaffPostDto();

        when(franchiseeRepository.existsFranchiseeByAbn(any()))
                .thenReturn(true);

        assertThrows(ResourceAlreadyExistException.class,
                () -> franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto));

    }

    @Test
    void shouldThrowResourceNotFoundExist() {
        SuburbListPostDto suburbListPostDto = TestHelper.createSuburbListPostDto();

        when(franchiseeRepository.findFranchiseeById(any()))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> franchiseeService.addDutyAreas(suburbListPostDto, 6L));

    }

}