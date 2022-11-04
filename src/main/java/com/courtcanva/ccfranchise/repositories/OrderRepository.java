package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByIdIn(List<Long> ids);

    @Modifying
    @Query("update Order set status='ACCPTED' where id = :id")
    void acceptOrder(Long id);
}
