package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.mappers.SuburbMapper;
import com.courtcanva.ccfranchise.mappers.SuburbMapperImpl;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.TestHelper;
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
        suburbRepository.save(TestHelper.suburb1());
        suburbRepository.save(TestHelper.suburb2());
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
    void shouldGetSuburbListDtoWhenGetAllSuburbs() {
        List<Suburb> suburbList = TestHelper.createSuburbsListWithFranchisee();

        when(suburbRepository.findAll()).thenReturn(suburbList);

        SuburbListGetDto suburbListGetDto = suburbService.findAllSuburbs();
        assertEquals(TestHelper.createSuburbListGetDto(), suburbListGetDto);

    }

    @Test
    void shouldReturnSuburbListWhenSuburbIsExist() {
        List<Suburb> suburbList = TestHelper.createSuburbsListWithFranchisee();
        SuburbListPostDto suburbListPostDto = TestHelper.createSuburbListPostDto();

        when(suburbRepository.findBySscCodeIn(any())).thenReturn(suburbList);

        List<Suburb> getSuburbList = suburbService.findSuburbBySscCodes(suburbListPostDto.getSuburbs().stream().map(SuburbPostDto::getSscCode).collect(Collectors.toList()));

        assertEquals(TestHelper.createSuburbsListWithFranchisee().get(1).getSscCode(), getSuburbList.get(1).getSscCode());
    }
}
