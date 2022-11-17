package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.models.Franchisee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.courtcanva.ccfranchise.constants.AUState.VIC;
import static org.mockito.Mockito.mock;


public class FranchiseeTestHelper {
    public static Franchisee createFranchiseeWithId() {

        return Franchisee.builder()
                .id(1234L)
                .contactNumber("0434666666")
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .legalEntityName("XX Company")
                .abn("12345678900")
                .state(VIC)
                .build();
    }

    public static Franchisee createMockFranchisee() {

        Franchisee mockFranchisee = mock(Franchisee.class);
        mockFranchisee.setId(1234L);
        mockFranchisee.setContactNumber("0434666666");
        mockFranchisee.setBusinessName("AAAAA");
        mockFranchisee.setBusinessAddress("zetland NSWssss");
        mockFranchisee.setAbn("12345678900");
        return mockFranchisee;
    }


    public static Optional<Franchisee> createOptionalFranchisee() {

        Franchisee franchisee = createMockFranchisee();

        Optional<Franchisee> optionalFranchisee = Optional.of(franchisee);
        optionalFranchisee.get().setId(1234L);
        optionalFranchisee.get().setContactNumber("0434666666");
        optionalFranchisee.get().setBusinessName("AAAAA");
        optionalFranchisee.get().setBusinessAddress("zetland NSWssss");
        optionalFranchisee.get().setAbn("12345678900");

        return optionalFranchisee;

    }

    public static Franchisee createFranchisee() {

        return Franchisee.builder()
                .contactNumber("0434666666")
                .businessName("AAAAA")
                .legalEntityName("COUNTCANVA")
                .state(AUState.ACT)
                .postcode(1234)
                .businessAddress("zetland NSWssss")
                .abn("12345678900")
                .build();
    }

    public static FranchiseePostDto createFranchiseePostDto() {

        return FranchiseePostDto.builder()
                .businessName("AAAAA")
                .contactNumber("0434666666")
                .businessAddress("zetland NSWssss")
                .legalEntityName("AAAA info")
                .state(AUState.ACT)
                .abn("12345678900")
                .build();
    }

    public static FranchiseeGetDto createFranchiseeGetDto() {

        return FranchiseeGetDto.builder()
                .franchiseeId(1234L)
                .abn("12345678900")
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
                .contactNumber("0434666666")
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("12345678900")
                .dutyAreas(SuburbTestHelper.createSuburbSet())
                .build();

    }

    public static Franchisee createFranchiseeWithOrderAssignmentSet(){
        return Franchisee.builder()
                .id(1234L)
                .contactNumber("0434666666")
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("12345678900")
                .dutyAreas(SuburbTestHelper.createOneSuburbSet())
                .orderAssignmentSet(OrderAssignmentTestHelper.orderAssignmentSet())
                .build();
    }

    public static List<Franchisee> createFranchiseeList() {
        List<Franchisee> franchisees = new ArrayList<>();
        franchisees.add(createFranchiseeWithDutyAreas());

        return franchisees;
    }

    public static List<Franchisee> createFranchiseeListWithOrderAssignment() {
        List<Franchisee> franchisees = new ArrayList<>();
        Franchisee franchisee = createFranchiseeWithOrderAssignmentSet();

        franchisees.add(franchisee);

        return franchisees;
    }
}
