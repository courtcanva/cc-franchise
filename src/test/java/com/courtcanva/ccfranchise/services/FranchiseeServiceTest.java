package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.*;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.*;
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

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FranchiseeServiceTest {

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private StaffService staffService;

    private FranchiseeService franchiseeService;

    private SuburbService suburbService;

    private SuburbRepository suburbRepository;

    private final FranchiseeAndStaffPostDto franchiseeAndStaffPostDto = new FranchiseeAndStaffPostDto(new FranchiseePostDto("CourtCanva", "CourtCanva LTD", "12312123111", "123456789", "Melbourne", AUState.VIC, 3000), new StaffPostDto("Taylor", "Swift", "taylor.s@gmail.com", "123456789", "abc st", 3000, AUState.VIC, "sdjkhsd"));


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

    private final Suburb mockSuburb = Suburb.builder().suburbName("Melbourne").postcode(3000).sscCode(21003L).state(AUState.VIC).build();

    private final List<Suburb> mockSuburbList = new ArrayList<>();
    @Test
    void shouldAddSuburbListGetDto() {


        SuburbListPostDto suburbListPostDto = TestHelper.createSuburbListPostDto();

        Franchisee franchisee = TestHelper.createFranchiseeWithId();



        List<Suburb> suburbs = new ArrayList<>();
        suburbs.add(TestHelper.suburb1());
        suburbs.add(TestHelper.suburb2());


        franchisee.addDutyAreas(suburbs);
        when(franchiseeRepository.save(any())).thenReturn(franchisee);

    }


    @Test
    void shouldReturnTrueWhenResourceAlreadyExist() {

        String franchiseeAbn = "124";

        when(franchiseeRepository.existsFranchiseeByAbn(franchiseeAbn))
                .thenReturn(true);

        assertTrue(franchiseeService.checkFranchiseeIsExist(franchiseeAbn));
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

//    @Test
//    void shouldReturnSuburbsWhenSuburbsAreExist(){
//
//        List<SuburbGetDto> suburbGetDto = TestHelper.createSuburbListGetDto().getSuburbs();
//
//        List<Suburb> suburbs = suburbRepository.findBySscCodeIn(TestHelper.createSuburbListPostDto().getSuburbs().stream().map(SuburbPostDto::getSscCode).collect(Collectors.toList()))
//;
//        when(suburbRepository.findBySscCodeIn(TestHelper.createSuburbListPostDto().getSuburbs().stream().map(SuburbPostDto::getSscCode).collect(Collectors.toList())))
//                .thenReturn(suburbs);
//
//        assertEquals(suburbs.get(1),suburbGetDto.get(1));
//
//    }

    @Test
    void shouldThrowResourceNotFoundExist() {
        SuburbListPostDto suburbListPostDto = TestHelper.createSuburbListPostDto();

        when(franchiseeRepository.findFranchiseeById(any()))
                .thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> franchiseeService.addDutyAreas(suburbListPostDto, 6L));

    }

}