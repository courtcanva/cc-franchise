package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, OrderAssignmentId> {

    List<OrderAssignment> findByOrderIdIn(List<Long> order_id);
}
