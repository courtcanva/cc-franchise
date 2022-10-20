package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.utils.SuburbTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class SuburbRepositoryTest {

    @Autowired
    private SuburbRepository suburbRepository;

    @BeforeEach
    void setUp() {
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());
    }

    @Test
    public void givenSuburbSSCCode_whenFindSuburbBySSCCode_shouldReturnSuburb() {

        assertEquals(12287L, suburbRepository.findBySscCodeIn(SuburbTestHelper.createSuburbListPostDto().getSuburbs()
                        .stream()
                        .map(SuburbPostDto::getSscCode)
                        .collect(Collectors.toList()))
                .get(1)
                .getSscCode());
    }

}
