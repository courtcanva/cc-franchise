package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.constants.DutyAreaFilterMode;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.models.Suburb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuburbTestHelper {

    public static SuburbListAndFilterModePostDto createSuburbListPostDtoWithIncludeMode() {
        List<SuburbPostDto> suburbs = new ArrayList<>();
        suburbs.add(new SuburbPostDto(11344L));
        suburbs.add(new SuburbPostDto(12287L));
        return SuburbListAndFilterModePostDto.builder().filterMode(DutyAreaFilterMode.INCLUDE).suburbs(suburbs).build();
    }

    public static SuburbListAndFilterModePostDto createSuburbListPostDtoWithExcludeMode() {
        List<SuburbPostDto> suburbs = new ArrayList<>();
        suburbs.add(new SuburbPostDto(11344L));
        suburbs.add(new SuburbPostDto(12287L));
        return SuburbListAndFilterModePostDto.builder().filterMode(DutyAreaFilterMode.EXCLUDE).suburbs(suburbs).build();
    }


    public static SuburbListGetDto createSuburbListGetDto() {
        List<SuburbGetDto> suburbs = new ArrayList<>();
        suburbs.add(new SuburbGetDto(11344L, "East Albury", 2640, AUState.NSW));
        suburbs.add(new SuburbGetDto(12287L, "Lavington", 2641, AUState.NSW));
        return SuburbListGetDto.builder().suburbs(suburbs).build();
    }

    public static List<Suburb> createSuburbsListWithFranchisee() {
        List<Suburb> suburbs = new ArrayList<>();
        suburbs.add(0, new Suburb(11344L, "East Albury", 2640, AUState.NSW, null));
        suburbs.add(1, new Suburb(12287L, "Lavington", 2641, AUState.NSW, null));
        return suburbs;
    }

    public static Suburb suburb1() {
        return Suburb.builder()
                .sscCode(11344L)
                .suburbName("East Albury")
                .postcode(2640)
                .state(AUState.NSW)
                .build();
    }

    public static Suburb suburb2() {
        return Suburb.builder()
                .sscCode(12287L)
                .suburbName("Lavington")
                .postcode(2641)
                .state(AUState.NSW)
                .build();
    }

    public static Suburb suburbWithFranchisee1() {
        return Suburb.builder()
                .sscCode(11344L)
                .suburbName("East Albury")
                .postcode(2640)
                .state(AUState.NSW)
                .availableFranchisees(FranchiseeTestHelper.createFranchiseeSet())
                .build();
    }

    public static Suburb suburbWithFranchisee2() {
        return Suburb.builder()
                .sscCode(12287L)
                .suburbName("Lavington")
                .postcode(2641)
                .state(AUState.NSW)
                .availableFranchisees(FranchiseeTestHelper.createFranchiseeSet())
                .build();
    }

    public static Set<Suburb> createSuburbSet() {
        Set<Suburb> suburbs = new HashSet<>();
        suburbs.add(suburbWithFranchisee1());
        suburbs.add(suburbWithFranchisee2());

        return suburbs;
    }
}
