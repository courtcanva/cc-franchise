package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, Long> {

}
