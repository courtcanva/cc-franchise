package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.models.Franchisee;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.courtcanva.ccfranchise.constants.AUState.VIC;
import static org.mockito.Mockito.mock;


public class FranchiseeTestHelper {
    public static Franchisee createFranchiseeWithId() {
        return Franchisee.builder()
                .id(1234L)
                .contactNumber("234567891")
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .legalEntityName("XX Company")
                .abn("12312123111")
                .state(VIC)
                .build();
    }

    public static Franchisee createMockFranchisee() {
        Franchisee mockFranchisee = mock(Franchisee.class);
        mockFranchisee.setId(1234L);
        mockFranchisee.setContactNumber("234567891");
        mockFranchisee.setBusinessName("AAAAA");
        mockFranchisee.setBusinessAddress("zetland NSWssss");
        mockFranchisee.setAbn("12312123111");
        return mockFranchisee;
    }


    public static Optional<Franchisee> createOptionalFranchisee() {
        Franchisee franchisee = createMockFranchisee();

        Optional<Franchisee> optionalFranchisee = Optional.of(franchisee);
        optionalFranchisee.get().setId(1234L);
        optionalFranchisee.get().setContactNumber("234567891");
        optionalFranchisee.get().setBusinessName("AAAAA");
        optionalFranchisee.get().setBusinessAddress("zetland NSWssss");
        optionalFranchisee.get().setAbn("12312123111");

        return optionalFranchisee;
    }

    public static Franchisee createFranchisee() {
        return Franchisee.builder()
                .contactNumber("234567891")
                .businessName("AAAAA")
                .legalEntityName("COUNTCANVA")
                .state(AUState.ACT)
                .postcode(1234)
                .businessAddress("zetland NSWssss")
                .abn("12312123111")
                .build();
    }

    public static FranchiseePostDto createFranchiseePostDto() {
        return FranchiseePostDto.builder()
                .businessName("AAAAA")
                .contactNumber("234567891")
                .businessAddress("zetland NSWssss")
                .legalEntityName("AAAA info")
                .state(AUState.ACT)
                .abn("12312123111")
                .build();
    }

    public static FranchiseeGetDto createFranchiseeGetDto() {
        return FranchiseeGetDto.builder()
                .franchiseeId(1234L)
                .abn("12345678901")
                .build();
    }

    public static Set<Franchisee> createFranchiseeSet() {
        Set<Franchisee> franchisees = new HashSet<>();
        franchisees.add(createFranchiseeWithId());
        return franchisees;
    }

    public static Franchisee createFranchiseeWithDutyAreas() {
        return Franchisee.builder()
                .id(1234L)
                .contactNumber("234567891")
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("12312123111")
                .dutyAreas(SuburbTestHelper.createSuburbSet())
                .build();
    }
}
