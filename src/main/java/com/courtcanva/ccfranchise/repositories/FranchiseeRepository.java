package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {

    boolean existsFranchiseeByAbn(String abn);

    Franchisee findFranchiseeById(Long franchiseeId);

    Franchisee addDutyAreas(List<Suburb> suburbs);

}
