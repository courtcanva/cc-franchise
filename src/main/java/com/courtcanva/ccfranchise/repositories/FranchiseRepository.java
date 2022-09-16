package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise,Long> {
}
