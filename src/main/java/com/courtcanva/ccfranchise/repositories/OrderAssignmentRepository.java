package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.dtos.orderAssignments.OrderAssignmentGetDto;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, OrderAssignmentId> {

    OrderAssignment findOrderAssignmentById(OrderAssignmentId orderAssignmentId);

}
