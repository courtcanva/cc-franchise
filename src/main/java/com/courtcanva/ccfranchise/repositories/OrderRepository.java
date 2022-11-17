package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import com.courtcanva.ccfranchise.constants.OrderStatus;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByIdIn(List<Long> ids);

    List<Order> findFirst10ByFranchiseeIdAndStatus(Long franchiseeId, OrderStatus statusCode);

//    OrderDetails findDetailsById(Long franchiseeId ,Long orderId);
}
