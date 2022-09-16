package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.constants.StaffRole;
import com.courtcanva.ccfranchise.constants.VerifyStatus;
import com.courtcanva.ccfranchise.model.Franchise;
import com.courtcanva.ccfranchise.model.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.HashSet;

import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class StaffRepositoryTest {

    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private FranchiseRepository franchiseRepository;


    @Test
    public void saveStaff(){
        Franchise franchise = Franchise.builder()
                .staffs(new HashSet<>())
                .id(1L)
                .ABN(12312)
                .createdTime(OffsetDateTime.now(UTC))
                .updatedTime(OffsetDateTime.now(UTC))
                .status(VerifyStatus.UNVERIFIED)
                .dutyArea("2012,12332,2131,12321")
                .address("nsw zetland")
                .postcode(2012)
                .name("CCCVVVV")
                .build();

        Staff staff = Staff.builder()
                .id(21L)
                .address("zetlan nsw")
                .email("ggg@gmail.com")
                .role(StaffRole.ADMIN)
                .firstName("sdafdsaf")
                .lastName("canva")
                .state("NSW")
                .status(VerifyStatus.UNVERIFIED)
                .createdTime(OffsetDateTime.now(UTC))
                .updatedTime(OffsetDateTime.now(UTC))
                .franchise(franchise)
                .build();

        System.out.println("staff = " + staff);
        System.out.println("franchise = " + franchise);
        franchiseRepository.save(franchise);
        staffRepository.save(staff);

        assertEquals("NSW",staffRepository.getReferenceById(21L).getState());

    }

}