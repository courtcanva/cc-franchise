package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.State;
import com.courtcanva.ccfranchise.dtos.StaffInfoDto;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.model.Franchisee;
import com.courtcanva.ccfranchise.model.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private FranchiseeService franchiseeService;
    @Mock
    private StaffMapper staffMapper;

    @InjectMocks
    private StaffService staffService;

    @Test
    void shouldCreatedStaffSuccessful() {

        StaffInfoDto staffDto = StaffInfoDto.builder()
                .residentialAddress("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state(State.ACT)
                .postcode(3213)
                .password("Bfasd1999324")
                .phoneNumber("31232131")
                .firstName("first")
                .lastName("last")
                .build();

        Franchisee mockFranchisee = Franchisee.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("1231232")
                .build();
        Staff mockStaff = Staff.builder()
                .id(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();

        when(staffMapper.toStaffEntity(any())).thenReturn(mockStaff);
        when(franchiseeService.findFranchiseeByABN(any()))
                .thenReturn(mockFranchisee);
        when(staffRepository.save(any())).thenReturn(mockStaff);

        Staff staff = staffService.createStaff(staffDto);

        assertEquals(1232L, staff.getId());


    }
}