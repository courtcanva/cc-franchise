package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.model.Franchisee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FranchiseeRepository extends JpaRepository<Franchisee,Long> {

    Optional<Franchisee> findByAbn(String abn);
}
