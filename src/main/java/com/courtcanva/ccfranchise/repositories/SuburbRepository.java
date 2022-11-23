package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SuburbRepository extends JpaRepository<Suburb, Long> {
    List<Suburb> findBySscCodeIn(List<Long> sscCodes);

    Optional<Suburb> findAllByPostcode(int postcode);



}
