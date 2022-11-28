package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.models.OrderAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.OffsetDateTime;
import java.util.List;

public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, OrderAssignmentId> {
    List<OrderAssignment> findOrderAssignmentByIdIn(List<OrderAssignmentId> ids);

    List<OrderAssignment> findOrderAssignmentByAssignedTimeBefore(OffsetDateTime dateTime);
}
