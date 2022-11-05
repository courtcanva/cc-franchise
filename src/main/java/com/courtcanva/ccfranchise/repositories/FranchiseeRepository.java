package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {

    boolean existsFranchiseeByAbn(String abn);

    Optional<Franchisee> findFranchiseeById(Long franchiseeId);

//    Optional<List<Franchisee>> findFranchiseeByDutyAreas(Set<Suburb> dutyAreas);

    //    @Query("SELECT f from Franchisee f, Suburb s, DutyArea d where  f.id = d.franchisee_id AND d.sscCode = s.ssc_Code AND s.postcode = :postcode")
    @Query(value = "SELECT * From franchisee f, suburb s,duty_area d where f.id = d.franchisee_id and d.ssc_code = s.ssc_code AND s.postcode = :postcode", nativeQuery = true)
    List<Franchisee> findFranchiseesByPostcode(@Param("postcode") int postcode);

    List<Franchisee> findFranchiseesByDutyAreas(Long sscCode);

}
