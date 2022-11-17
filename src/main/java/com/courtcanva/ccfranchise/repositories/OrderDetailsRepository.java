package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails findByIdIn(Long orderId);

}
