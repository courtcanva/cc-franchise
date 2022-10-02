package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Franchisee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {

    boolean existsFranchiseeByAbn(String abn);

    Franchisee findFranchiseeById(Long franchiseeId);

}
