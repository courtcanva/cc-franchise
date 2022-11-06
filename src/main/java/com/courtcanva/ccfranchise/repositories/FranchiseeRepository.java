package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {

    boolean existsFranchiseeByAbn(String abn);

    Optional<Franchisee> findFranchiseeById(Long franchiseeId);

    @Query(value = "SELECT * FROM franchise.franchisee " +
            "Where id IN " +
            "(Select f.id " +
            "FROM " +
              "franchise.franchisee AS f, " +
              "franchise.suburb AS s, " +
              "franchise.duty_area AS d , " +
              "franchise.\"order\" AS o " +
            "WHERE "+
              "f.\"id\" = d.franchisee_id " +
              "AND d.ssc_code = s.ssc_code " +
              "AND f.\"id\" = o.franchisee_id " +
              "AND s.postcode =:postcode " +
            "GROUP BY f.\"id\" " +
            "HAVING COUNT (*) < 10) "+
            "ORDER BY business_name", nativeQuery = true)
    List<Franchisee> findFranchiseesByPostcode(@Param("postcode") int postcode);


}
