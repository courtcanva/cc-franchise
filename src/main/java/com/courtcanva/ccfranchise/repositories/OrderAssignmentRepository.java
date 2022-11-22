package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.OffsetDateTime;
import java.util.List;

public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, Long> {
    List<OrderAssignment> findOrderAssignmentByIdIn(List<Long> ids);

    List<OrderAssignment> findOrderAssignmentByCreatedTimeBefore(OffsetDateTime dateTime);
}
