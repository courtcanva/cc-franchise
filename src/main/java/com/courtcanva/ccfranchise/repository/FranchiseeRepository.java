package com.courtcanva.ccfranchise.repository;

import com.courtcanva.ccfranchise.dto.Franchisee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {
    @Query("FROM Franchisee where email=:email")
    Optional<Franchisee> findOneByEmail(@Param("email") String email);
}
