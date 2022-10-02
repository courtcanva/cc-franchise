package com.courtcanva.ccfranchise.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SuburbRepositoryTest {

    @Autowired
    private SuburbRepository suburbRepository;

}
