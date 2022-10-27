package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.mappers.SuburbMapper;
import com.courtcanva.ccfranchise.mappers.SuburbMapperImpl;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.SuburbTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuburbServiceTest {

    @Mock
    private SuburbRepository suburbRepository;

    private SuburbService suburbService;

    @BeforeEach
    void setUp() {
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());
    }

    @BeforeEach
    public void setSuburbServiceUp() {
        SuburbMapper suburbMapper = new SuburbMapperImpl();
        suburbService = new SuburbService(
                suburbRepository,
                suburbMapper
        );
    }

    @Test
    void whenGetAllSuburbs_shouldGetSuburbListDto() {
        List<Suburb> suburbList = SuburbTestHelper.createSuburbsListWithFranchisee();

        when(suburbRepository.findAll()).thenReturn(suburbList);

        SuburbListGetDto suburbListGetDto = suburbService.findAllSuburbs();
        assertEquals(SuburbTestHelper.createSuburbListGetDto(), suburbListGetDto);

    }

    @Test
    void givenSSCCodeAndSuburbExists_whenFindSuburbBySSCCode_shouldReturnSuburbList() {
        List<Suburb> suburbList = SuburbTestHelper.createSuburbsListWithFranchisee();
        SuburbListAndFilterModePostDto suburbListAndFilterModePostDto = SuburbTestHelper.createSuburbListPostDtoWithIncludeMode();

        when(suburbRepository.findBySscCodeIn(any())).thenReturn(suburbList);

        List<Suburb> getSuburbList = suburbService.findSuburbBySscCodes(suburbListAndFilterModePostDto.getSuburbs().stream().map(SuburbPostDto::getSscCode).collect(Collectors.toList()));

        assertEquals(SuburbTestHelper.createSuburbsListWithFranchisee().get(1).getSscCode(), getSuburbList.get(1).getSscCode());
    }
}
