package com.courtcanva.ccfranchise.quartz.jobs;

import com.courtcanva.ccfranchise.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignOrderJob {

    @Autowired
    private OrderService orderService;

    public void assignOrders() {
        orderService.assignOrders();
    }

}
