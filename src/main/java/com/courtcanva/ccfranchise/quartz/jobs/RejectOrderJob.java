package com.courtcanva.ccfranchise.quartz.jobs;

import com.courtcanva.ccfranchise.services.OrderAssignmentService;
import com.courtcanva.ccfranchise.services.OrderService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class RejectOrderJob {
    @Autowired
    private OrderService orderService;

    public void rejectAllExpriedOrders() {
        orderService.rejectAllExpriedOrders();
    }
}
