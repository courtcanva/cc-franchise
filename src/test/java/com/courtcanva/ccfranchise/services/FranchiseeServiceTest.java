package com.courtcanva.ccfranchise.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapperImpl;
import com.courtcanva.ccfranchise.mappers.OrderDisplayMapper;
import com.courtcanva.ccfranchise.mappers.OrderDisplayMapperImpl;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class FranchiseeServiceTest {

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private OrderRepository orderRepository;


    @Mock
    private StaffService staffService;

    private FranchiseeService franchiseeService;


    @BeforeEach
    public void setFranchiseeServiceUp() {

        FranchiseeMapper franchiseeMapper = new FranchiseeMapperImpl();
        StaffMapper staffMapper = new StaffMapperImpl();
        OrderDisplayMapper orderDisplayMapper = new OrderDisplayMapperImpl();
        franchiseeService = new FranchiseeService(
            franchiseeRepository, orderRepository,
            franchiseeMapper, staffMapper, orderDisplayMapper,
            staffService);
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

}