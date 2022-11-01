package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.OrderAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, Long> {
    @Modifying
    @Transactional//TODO: Need to delete after testing
    @Query("update OrderAssignment set status='reject' where createdTime < :rejectTime")
    int rejectAllExpiredOrders(@Param("rejectTime") OffsetDateTime rejectTime);

    //TODO: Need to delete after testing
    @Query("select order from OrderAssignment order where order.createdTime < :rejectTime")
    List<OrderAssignment> findAllExpiredOrders(@Param("rejectTime") OffsetDateTime rejectTime);
}
