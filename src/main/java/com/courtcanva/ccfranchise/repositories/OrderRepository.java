package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByIdIn(List<Long> ids);

    List<Order> findOrdersByFranchiseeAndStatusInOrderByStatusAscCreatedTime(Franchisee franchisee,
                                                                             List<OrderStatus> completed,
                                                                             PageRequest pageRequest);

    List<Order> findFirst10ByFranchiseeIdAndStatus(Long franchiseeId, OrderStatus statusCode);

}
