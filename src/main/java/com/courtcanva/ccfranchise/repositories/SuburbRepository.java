package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuburbRepository extends JpaRepository<Suburb, Long> {
    List<Suburb> findBySscCodeIn(List<Long> sscCodes);

}
