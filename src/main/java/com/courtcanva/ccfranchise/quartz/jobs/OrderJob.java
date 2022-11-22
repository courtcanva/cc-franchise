package com.courtcanva.ccfranchise.quartz.jobs;

import com.courtcanva.ccfranchise.services.OrderAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderJob {
    @Autowired
    private OrderAssignmentService orderService;

    public void rejectAllExpriedOrders() throws InterruptedException {
        orderService.rejectAllExpriedOrders();
    }
}
