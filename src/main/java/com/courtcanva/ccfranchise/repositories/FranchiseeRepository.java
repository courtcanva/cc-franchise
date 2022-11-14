package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndOrderNumber;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FranchiseeRepository extends JpaRepository<Franchisee, Long> {

    boolean existsFranchiseeByAbn(String abn);

    List<Franchisee> findFranchiseesByDutyAreasIn(Set<Suburb> dutyAreas);

    Optional<Franchisee> findFranchiseeById(Long franchiseeId);

    List<Franchisee> findByIdIn(List<Long> ids);

//    @Query(value = "SELECT f.id FROM franchise.franchisee f LEFT JOIN franchise.\"order\" o ON o.franchisee_id = f.id WHERE o.id IS NULL", nativeQuery = true)
//    List<Long> findFranchiseesHaveNoOrder();
//
//    @Query(value = "SELECT franchisee.id FROM franchise.franchisee INNER JOIN franchise.duty_area ON duty_area.franchisee_id = franchisee.id WHERE duty_area.ssc_code = :sscCode ORDER BY business_name", nativeQuery = true)
//    List<Long> findFranchiseesByDutyArea(@Param("sscCode") int sscCode);
//
//    @Query("select new com.courtcanva.ccfranchise.dtos.FranchiseeAndOrderNumber(o.franchisee.id, count (o.franchisee.id)) " +
//            "from Order as o where o.status in (com.courtcanva.ccfranchise.constants.OrderStatus.ASSIGNED_PENDING, com.courtcanva.ccfranchise.constants.OrderStatus.ACCEPTED) " +
//            "group by o.franchisee.id having count (o.franchisee.id) < 10")
//    List<FranchiseeAndOrderNumber> findFranchiseesOrderNumberLessTen();
//
//    @Query(value = "SELECT franchisee.id FROM franchise.franchisee INNER JOIN franchise.order_assignment ON franchisee.id = order_assignment.franchisee_id WHERE order_assignment.status = 'REJECTED' AND order_assignment.order_id = :orderId", nativeQuery = true)
//    List<Long> findFranchiseesWhoIsRejected(@Param("orderId") Long orderId);


}
