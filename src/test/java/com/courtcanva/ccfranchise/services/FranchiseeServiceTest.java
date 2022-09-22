package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapperImpl;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FranchiseeServiceTest {

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private StaffService staffService;


    private FranchiseeService franchiseeService;

    private TestHelper testHelper;

    @BeforeEach
    public void setFranchiseeServiceUp() {
        testHelper = new TestHelper();
        FranchiseeMapper franchiseeMapper = new FranchiseeMapperImpl();
        StaffMapper staffMapper = new StaffMapperImpl();
        franchiseeService = new FranchiseeService(
                franchiseeRepository,
                franchiseeMapper,
                staffMapper,
                staffService
        );
    }

    @Test
    void shouldCreateStaffAndFranchiseeGetDto() {

        Franchisee franchisee = testHelper.createFranchisee();
        StaffGetDto staffGetDto = testHelper.createStaffGetDto();

        FranchiseePostDto franchiseePostDto = testHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = testHelper.createStaffPostDto();

        when(franchiseeRepository.save(any())).thenReturn(franchisee);
        when(staffService.createStaff(any())).thenReturn(staffGetDto);

        FranchiseeAndStaffDto franchiseeAndStaffDto = franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto);
        assertEquals(1234L, franchiseeAndStaffDto.getFranchiseeGetDto().getFranchiseeId());
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


        FranchiseePostDto franchiseePostDto = testHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = testHelper.createStaffPostDto();

        when(franchiseeRepository.existsFranchiseeByAbn(any()))
                .thenReturn(true);

        assertThrows(ResourceAlreadyExistException.class,
                () -> franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto));

    }

}