package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FranchiseRepository extends JpaRepository<Franchise,Long> {

    Optional<Franchise> findByABN(Integer abn);
}
