package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {

    boolean existsFranchiseeByAbn(String abn);

    List<Franchisee> findFranchiseesByDutyAreasIn(Set<Suburb> dutyAreas);

    Optional<Franchisee> findFranchiseeById(Long franchiseeId);

    List<Franchisee> findByIdIn(List<Long> ids);



}
