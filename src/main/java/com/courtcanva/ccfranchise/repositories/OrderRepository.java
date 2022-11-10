package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import com.courtcanva.ccfranchise.constants.OrderStatus;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByIdIn(List<Long> ids);

    List<Order> findFirst10ByFranchiseeIdAndStatus(Long franchiseeId, OrderStatus statusCode);

    @Query(value = "SELECT * FROM franchise.\"order\" WHERE status = 'UNASSIGNED' ORDER BY created_at", nativeQuery = true)
    List<Order> findAllUnAssignedOrders();
}
